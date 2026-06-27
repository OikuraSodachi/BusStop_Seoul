package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import com.todokanai.busstop_seoul.Constants
import com.todokanai.busstop_seoul.Constants.ZOOM_ON_MARKER_CLICK
import com.todokanai.busstop_seoul.RangeSelectionColors
import com.todokanai.busstop_seoul.util.toMapHue
import com.todokanai.busstop_seoul.compose.buttons.MenuButton
import com.todokanai.busstop_seoul.compose.list.RangeSearchResultList
import com.todokanai.busstop_seoul.compose.list.RangeSelectionPointList
import com.todokanai.busstop_seoul.compose.map.MainMap
import com.todokanai.busstop_seoul.compose.map.SmallMap
import com.todokanai.busstop_seoul.compose.navigation.navigateToLineInfo
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.interfaces.compose.MainMapInterface
import com.todokanai.busstop_seoul.interfaces.compose.MapScreenMode
import com.todokanai.busstop_seoul.interfaces.compose.MenuButtonInterface
import com.todokanai.busstop_seoul.viewmodel.MapViewModel

@Composable
fun MapScreen(
    navController: NavHostController,
    stId: Long? = null,
    viewModel: MapViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    var targetStationId by rememberSaveable { mutableStateOf(stId) }       // navController.navigate() 해도 targetStId 값 유지

    // 통합된 모드 상태 관리
    var screenMode by remember { mutableStateOf<MapScreenMode>(MapScreenMode.Normal()) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(Constants.DEFAULT_LATITUDE,Constants.DEFAULT_LONGITUDE), Constants.DEFAULT_ZOOM)
    }

    val mainMapInterface = object : MainMapInterface {
        override fun onCameraPositionChanged(latLngBounds: LatLngBounds) {
            viewModel.testCameraPositionChanged(latLngBounds, cameraPositionState.position.zoom)
        }

        override fun onMarkerClick(markerInfo: MarkerInfo) {
            val stationInfo = markerInfo.stationInfo

            // 모드에 따른 분기 처리
            when (val mode = screenMode) {
                is MapScreenMode.Normal -> {
                    screenMode = mode.copy(targetStation = stationInfo)
                    targetStationId = stationInfo.stId
                }
                is MapScreenMode.RangeSelection -> {
                    screenMode = mode.updateGroupItems(stationInfo)
                }
            }
        }

        override fun markerColorSelector(stId: Long): Float {
            return when (val mode = screenMode) {
                is MapScreenMode.RangeSelection -> {
                    if (mode.startGroup.map{it.stId}.contains(stId)) RangeSelectionColors.startColor.toMapHue()
                    else if (mode.endGroup.map{it.stId}.contains(stId)) RangeSelectionColors.endColor.toMapHue()
                    else BitmapDescriptorFactory.HUE_RED
                }
                else -> BitmapDescriptorFactory.HUE_RED
            }
        }
    }

    val menuButtonInterface = object : MenuButtonInterface {
        override fun toggleSmallMap() = viewModel.saveSmallMapEnabled(!uiState.value.isSmallMapEnabled)
        override fun enableRotation() = viewModel.saveRotationGesturesEnabled(!uiState.value.rotationGesturesEnabled)

        override fun toggleRangeSelectionMode() {
            screenMode = if (screenMode is MapScreenMode.Normal) {
                MapScreenMode.RangeSelection() // 모드 전환 시 targetStation 자동 소멸
            } else {
                MapScreenMode.Normal()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // 상단 UI 분기 (RangeSelection 모드일 때만 표시)
        if (screenMode is MapScreenMode.RangeSelection) {
            val mode = screenMode as MapScreenMode.RangeSelection
            RangeSelectionMenu(
                isStartMode = mode.isStartMode(),
                onToggleGroupView = { screenMode = mode.copy(isGroupViewEnabled = !mode.isGroupViewEnabled) },
                onToggleSearchResult = { screenMode = mode.copy(isSearchResultEnabled = !mode.isSearchResultEnabled) },
                selectStartRange = { screenMode = mode.toStartMode() },
                selectEndRange = { screenMode = mode.toEndMode() }
            )
        }

        MapScreenBox(
            cameraPositionState = cameraPositionState,
            mainMapInterface = mainMapInterface,
            menuButtonInterface = menuButtonInterface,
            isSmallMapEnabled = uiState.value.isSmallMapEnabled,
            zoomControlsEnabled = uiState.value.zoomControlsEnabled,
            mapToolbarEnabled = uiState.value.mapToolbarEnabled,
            rotationGesturesEnabled = uiState.value.rotationGesturesEnabled,
            markerInfos = uiState.value.markerInfos,
            modifier = Modifier.weight(1f)
        )

        // 하단 정류소 정보 UI 분기
        if (screenMode is MapScreenMode.Normal) {
            val target = (screenMode as MapScreenMode.Normal).targetStation
            if (target != null) {
                LaunchedEffect(target) {
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(
                        LatLng(target.tmY, target.tmX),
                        ZOOM_ON_MARKER_CLICK
                    )
                }
                StationInfoScreen(
                    arsId = target.arsId,
                    stName = target.stNm,
                    getArriveInfos = { viewModel.getArriveInfos(it) },
                    onClose = { screenMode = MapScreenMode.Normal(null) },
                    toLineInfoScreen = { navController.navigateToLineInfo(it,target.stId) }
                )
            }
        }else if(screenMode is MapScreenMode.RangeSelection){
            Row {
                val mode = screenMode as MapScreenMode.RangeSelection
                if (mode.isGroupViewEnabled) {
                    val itemList =
                        if (mode.isStartMode()) mode.startGroup else mode.endGroup
                    RangeSelectionPointList(
                        rangeSelectionPointList = itemList,
                        onItemClick = { screenMode = mode.updateGroupItems(it) },
                        modifier = Modifier.weight(1f)
                    )
                }

                if (mode.isSearchResultEnabled) {
                    RangeSearchResultList(
                        startGroup = mode.startGroup.map{it.arsId},
                        endGroup = mode.endGroup.map{it.arsId},
                        rangeSearchResultList = { start, end ->
                            viewModel.rangeSearchResult(start, end)
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // 초기 진입 시 stId 처리
        LaunchedEffect(key1 = targetStationId) {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(viewModel.lastKnownLatLng(), viewModel.lastKnownZoomLevel())
            val info = viewModel.getStationInfo(targetStationId)
            if (info != null) screenMode = MapScreenMode.Normal(info)
        }
    }
}

@Composable
private fun MapScreenBox(
    cameraPositionState: CameraPositionState,
    mainMapInterface: MainMapInterface,
    menuButtonInterface: MenuButtonInterface,
    isSmallMapEnabled:Boolean,
    zoomControlsEnabled:Boolean,
    mapToolbarEnabled:Boolean,
    rotationGesturesEnabled:Boolean,
    markerInfos:List<MarkerInfo>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        MainMap(
            cameraPositionState = cameraPositionState,
            zoomControlsEnabled = zoomControlsEnabled,
            mapToolbarEnabled = mapToolbarEnabled,
            rotationGesturesEnabled = rotationGesturesEnabled,
            markerInfos = markerInfos,
            mainMapCallback = mainMapInterface
        )
        MenuButton(
            menuButtonInterface,
            rotationGesturesEnabled
        )
        if (isSmallMapEnabled) {
            SmallMap(
                modifier = Modifier.align(Alignment.TopEnd),
                cameraPositionState = { smallMapCameraPositionState(cameraPositionState) }
            ) // Todo: MainMap 과 같은 가로/세로 비율을 유지할 것
        }
    }
}

private fun smallMapCameraPositionState(state:CameraPositionState): CameraPositionState {
    return CameraPositionState(
        position = CameraPosition(
            state.position.target,
            state.position.zoom / 2,
            state.position.tilt,
            state.position.bearing
        )
    )
}



// Todo: Composable parameter 를 interface 로 wrapping 하는게 나으려나?