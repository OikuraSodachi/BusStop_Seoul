package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import com.todokanai.busstop_seoul.Constants.ZOOM_ON_MARKER_CLICK
import com.todokanai.busstop_seoul.compose.buttons.MenuButton
import com.todokanai.busstop_seoul.compose.list.RangeSelectionPointList
import com.todokanai.busstop_seoul.compose.map.MainMap
import com.todokanai.busstop_seoul.compose.map.SmallMap
import com.todokanai.busstop_seoul.compose.navigation.navigateToLineInfo
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.dataclass.StationInfo
import com.todokanai.busstop_seoul.interfaces.compose.MainMapInterface
import com.todokanai.busstop_seoul.interfaces.compose.MenuButtonInterface
import com.todokanai.busstop_seoul.viewmodel.MapViewModel

private enum class SelectionType { START, END }

private sealed interface MapScreenMode {
    // 일반 모드: 특정 정류소를 선택할 수 있음
    data class Normal(val targetStation: StationInfo? = null) : MapScreenMode

    // 구간 선택 모드: 시작/종료 그룹을 관리함
    data class RangeSelection(
        val type: SelectionType = SelectionType.START,
        val startGroup: List<StationInfo> = emptyList(),
        val endGroup: List<StationInfo> = emptyList(),
        val isGroupViewEnabled: Boolean = false    // 선택된 목록 창 활성화 여부
    ) : MapScreenMode
}

@Composable
fun MapScreen(
    navController: NavHostController,
    stId: Long? = null,
    viewModel: MapViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    // 통합된 모드 상태 관리
    var screenMode by remember { mutableStateOf<MapScreenMode>(MapScreenMode.Normal()) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewModel.lastKnownLatLng(), viewModel.lastKnownZoomLevel())
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
                }
                is MapScreenMode.RangeSelection -> {
                    screenMode = if (mode.type == SelectionType.START) {
                        mode.copy(startGroup = rangeSelector(stationInfo, mode.startGroup))
                    } else {
                        mode.copy(endGroup = rangeSelector(stationInfo, mode.endGroup))
                    }
                }
            }
        }

        override fun markerColorSelector(stId: Long): Float {
            return when (val mode = screenMode) {
                is MapScreenMode.RangeSelection -> {
                    if (mode.startGroup.map{it.stId}.contains(stId)) BitmapDescriptorFactory.HUE_GREEN
                    else if (mode.endGroup.map{it.stId}.contains(stId)) BitmapDescriptorFactory.HUE_BLUE
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
                isStartMode = mode.type == SelectionType.START,
                onToggleGroupView = { screenMode = mode.copy(isGroupViewEnabled = !mode.isGroupViewEnabled) },
                selectStartRange = { screenMode = mode.copy(type = SelectionType.START) },
                selectEndRange = { screenMode = mode.copy(type = SelectionType.END) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
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
                    toLineInfoScreen = { navController.navigateToLineInfo(it) },
                    modifier = Modifier.height(400.dp).fillMaxWidth()
                )
            }
        }else if(screenMode is MapScreenMode.RangeSelection){
            val mode = screenMode as MapScreenMode.RangeSelection
            if(mode.isGroupViewEnabled){
                val type = mode.type
                val itemList = if(type == SelectionType.START) mode.startGroup else mode.endGroup
                RangeSelectionPointList(
                    rangeSelectionPointList = itemList,
                    onItemClick = {
                        if(type == SelectionType.START){
                            screenMode = mode.copy(startGroup = mode.startGroup.minus(it))
                        }else{
                            screenMode = mode.copy(endGroup = mode.endGroup.minus(it))
                        }
                    },
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }
        }

        // 초기 진입 시 stId 처리
        LaunchedEffect(key1 = stId) {
            val info = viewModel.getStationInfo(stId)
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
        MenuButton(menuButtonInterface)
        if (isSmallMapEnabled) {
            SmallMap(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .height(300.dp)
                    .width(180.dp),
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

/** startGroup, endGroup 에 targetStation 추가/제거
 *
 * @param targetStId 추가/제거할 정류소
 * @param group startGroup, endGroup
 * @return 수정된 목록 **/
private fun rangeSelector(targetStation:StationInfo, group:List<StationInfo>):List<StationInfo>{
    return if(group.contains(targetStation)){
        group.filter { it != targetStation }
    }else{
        group + targetStation
    }
}

// Todo: Composable parameter 를 interface 로 wrapping 하는게 나으려나?