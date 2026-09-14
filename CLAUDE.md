# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run all unit tests
./gradlew test

# Run a single test class
./gradlew :app:test --tests "com.example.busapp.ExampleUnitTest"

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Build a specific module
./gradlew :data:assembleDebug
./gradlew :domain:build
```

## Secrets Setup

Two files are required to build — neither is committed to version control:

- `local.properties` — must contain `REST_API_KEY=<Seoul_bus_API_key>` (used by `:data` module via `BuildConfig.REST_API_KEY`)
- `secrets.properties` — must contain `MAPS_API_KEY=<Google_Maps_key>` (injected into the manifest via the Maps Secrets plugin)

## Module Architecture

Three-module clean architecture:

```
:domain  ←  :data  ←  :app
```

- **`:domain`** — pure Kotlin/JVM (no Android), no external dependencies except `javax.inject` and coroutines. Defines repository interfaces (`StationRepository`, `LocalDataRepository`, `SettingsRepository`, etc.) and use cases (`BusUseCase`, `MapUseCase`, `SearchUseCase`). ViewModels depend only on this module.

- **`:data`** — Android library. Implements domain repository interfaces using:
  - Retrofit + SimpleXML → Seoul public bus APIs (`ws.bus.go.kr`)
  - Room (v2, `fallbackToDestructiveMigration`) → three entities: `StationItem`, `BusLineItem` (favorites IDs only), `HistoryItem` (with timestamps)
  - CSV assets (`SeoulBusStation.csv`, `SeoulBusLine.csv`) → full station and line data loaded into memory at startup via `LocalDataRepositoryImpl`
  - DataStore Preferences → map state (last lat/lng/zoom) and map UI settings via `BaseDataStore` abstract class (provides `.flow()`, `.save()`, `.notNullFlow()` extensions)

- **`:app`** — application module. Contains Hilt-injected ViewModels, Jetpack Compose screens, and navigation.

## ViewModel & State Management

Each screen has a dedicated `@HiltViewModel` that depends on one or more UseCases:

| ViewModel | UseCases | UiState |
|---|---|---|
| `MapViewModel` | `MapUseCase`, `BusUseCase` | `MapScreenUiState` |
| `MainScreenViewModel` | `BusUseCase` | `MainScreenUiState` |
| `SearchScreenViewModel` | `SearchUseCase`, `BusUseCase` | `SearchScreenUiState` |
| `LineInfoViewModel` | `BusUseCase` | `LineInfoScreenUiState` |

**Pattern:** All ViewModels merge multiple `Flow` sources into a single `StateFlow<UiState>` using `combine()` with `SharingStarted.WhileSubscribed(5000)`. UiState classes are plain data classes (not sealed). Collected in Composables via `collectAsStateWithLifecycle()`. One-shot fetches use `viewModelScope.launch { ... }`.

**Map screen mode** is managed via `MapScreenMode` sealed class: `MapScreenMode.Normal` vs `MapScreenMode.RangeSelection`.

## Key Patterns

**Favorites storage**: Room stores only the ID (`stId` or `busRouteId`). When favorites are retrieved, `LocalDataRepositoryImpl` joins those IDs back to the full objects loaded from CSV. The CSV data lives as an in-memory `Flow<List<...>>` created once at injection time.

**Settings persistence**: All map UI toggles and last-known camera position are persisted via DataStore in `SettingsRepositoryImpl`. `MapUseCase` exposes them as `Flow<T>` and provides `suspend` save functions. `MapViewModel` combines these flows into `MapScreenUiState`.

**Map markers**: Markers are only shown when the map radius (diagonal / 2) is less than `MAP_MARKER_MINIMUM_RADIUS` (2000 m). `MapViewModel.testCameraPositionChanged` recalculates this on every camera move and fires a `getStationByPosition` API call if the threshold is met.

**Search results**: `SearchResult` is an abstract class (`@Immutable`) with an abstract `id` property, subclassed by `StationSearchResult` and `LineSearchResult`. The `id` is used as a stable `key` in `LazyColumn`.

## Recomposition Optimization

Applied patterns (from the "Composable recomposition 최적화" commit):

- **`@Stable` on callback interfaces** (`MainMapInterface`, `MenuButtonInterface`) and `@Immutable` on result subclasses to suppress unnecessary recompositions
- **`remember { }` for interface implementations** passed into Composables — preserves object identity across recompositions
- **`remember(deps)` for expensive object creation** — e.g., `MapUiSettings` cached with setting flags as keys
- **`key = { _, item -> item.id }` in all `LazyColumn` / `itemsIndexed` calls** — ensures item identity is stable across list updates (applied in FavoritesTab, HistoryTab, LineInfoScreen, RangeSelectionPointList, RangeSearchResultList)
- **`rememberSaveable`** for navigation-persistent local state (e.g., `targetStationId` in `MapScreen`)

## Dependency Injection (Hilt)

- `DatabaseModule` (`@InstallIn(SingletonComponent::class)`) provides: Room Database, all three DAOs, all Repository implementations as `@Singleton`
- `AssetManager` has a custom `@Provides` so `LocalDataRepositoryImpl` can load CSVs from assets
- UseCases use constructor injection (no explicit `@Provides` needed)
- `MainActivity` is `@AndroidEntryPoint`

## Navigation

Single activity (`MainActivity`) with Compose Navigation. `BusNavHost` defines four destinations:

| Destination | Route | Notes |
|---|---|---|
| `MainScreen` | `MainScreen` | Start destination; Favorites + History tabs |
| `MapScreen` | `MapScreen?stIdArg={stId}` | Optional `stId` arg to pre-select a station |
| `LineInfoScreen` | `LineInfoScreen/{lineInfoArg}?targetStationIdArg={id}` | Route detail with optional target station |
| `SearchScreen` | `SearchScreen` | Text search over CSV data |

Extension functions on `NavHostController` (e.g. `navigateToMapScreen(stId)`) are the intended navigation API — use these rather than calling `navigate()` directly.

## API Endpoints

All calls are to Seoul's public transit open APIs (XML responses parsed via SimpleXML Retrofit converter):

- `StationInfoService` — station by name/UID/position, routes by station, bus times by station
- `BusPositionService` — bus positions by route ID
- `BusArriveInfoService` — arrival info for all stops on a route

`BuildConfig.REST_API_KEY` is embedded into the `@GET` path at compile time, so the key must be present in `local.properties` before building `:data`.

## Gradle & SDK

- Version catalog: `gradle/libs.versions.toml`
- compileSdk 36, minSdk 29, targetSdk 36, Java 17 / JVM 17
- Key versions: Kotlin 2.0.0, Compose BOM 2024.04.01, Hilt 2.48, Room 2.6.1, Lifecycle Compose 2.9.2

## Testing

No meaningful tests are implemented — only placeholder `ExampleUnitTest` and `ExampleInstrumentedTest` exist.
