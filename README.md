# BusStop Seoul

서울 버스 정류장 정보 및 실시간 도착 정보를 제공하는 Android 앱입니다.

## Tech Stack

### Language & Platform

| 항목 | 버전 |
|---|---|
| Kotlin | 2.0.0 |
| Android minSdk | 29 (Android 10) |
| Android compileSdk / targetSdk | 36 |
| Java compatibility | 17 |

### Architecture

3-모듈 Clean Architecture

```
:domain  ←  :data  ←  :app
```

- **`:domain`** — 순수 Kotlin/JVM 모듈. Repository 인터페이스 및 UseCase 정의
- **`:data`** — Android 라이브러리. Repository 구현체, 외부 API·DB·파일 접근
- **`:app`** — 애플리케이션 모듈. ViewModel, Compose UI, Navigation

### UI

| 라이브러리 | 버전 |
|---|---|
| Jetpack Compose BOM | 2024.04.01 |
| Material3 | 1.4.0 |
| Activity Compose | 1.10.1 |
| Compose UI Tooling Preview | 1.9.2 |
| Compose Material Icons Core | (BOM 관리) |

### Navigation

| 라이브러리 | 버전 |
|---|---|
| Navigation Compose | 2.9.7 |
| Hilt Navigation Compose | 1.0.0 |

### Dependency Injection

| 라이브러리 | 버전 |
|---|---|
| Hilt Android | 2.48 |
| KSP (Kotlin Symbol Processing) | 2.0.0-1.0.22 |

### Async / Reactive

| 라이브러리 | 버전 |
|---|---|
| Kotlinx Coroutines Core | 1.7.3 |
| Lifecycle Runtime Compose (collectAsStateWithLifecycle) | 2.9.2 |

### Network

| 라이브러리 | 버전 |
|---|---|
| Retrofit | 2.9.0 |
| Retrofit Converter SimpleXML | 2.9.0 |

서울 공공 버스 API(`ws.bus.go.kr`)의 XML 응답을 SimpleXML 컨버터로 파싱합니다.

### Local Storage

| 라이브러리 | 버전 |
|---|---|
| Room Runtime | 2.6.1 |
| Room KTX | 2.6.1 |
| DataStore Preferences | 1.1.4 |
| OpenCSV | 4.4 |

- **Room** — 즐겨찾기 ID, 히스토리 등 3개 Entity 관리
- **DataStore** — 마지막 카메라 위치, 지도 UI 설정 영속화
- **OpenCSV** — 앱 시작 시 `SeoulBusStation.csv` / `SeoulBusLine.csv` 를 메모리에 로드

### Maps

| 라이브러리 | 버전 |
|---|---|
| Google Play Services Maps | 19.2.0 |
| Maps Compose | 2.5.3 |
| Maps Secrets Gradle Plugin | 2.0.1 |

### Build Tools

| 항목 | 버전 |
|---|---|
| Android Gradle Plugin (AGP) | 8.13.0 |
| Gradle Version Catalog | `gradle/libs.versions.toml` |
| Compose Compiler Plugin | 2.0.0 |

## Secrets Setup

빌드 전 두 파일이 필요합니다 (버전 관리에 포함되지 않음):

- `local.properties` — `REST_API_KEY=<서울버스_API_키>` 추가 (`:data` 모듈 `BuildConfig` 주입)
- `secrets.properties` — `MAPS_API_KEY=<Google_Maps_키>` 추가 (Manifest 주입)

## Build Commands

```bash
# Debug APK 빌드
./gradlew assembleDebug

# Release APK 빌드
./gradlew assembleRelease

# 단위 테스트
./gradlew test

# 특정 모듈 빌드
./gradlew :data:assembleDebug
./gradlew :domain:build
```
