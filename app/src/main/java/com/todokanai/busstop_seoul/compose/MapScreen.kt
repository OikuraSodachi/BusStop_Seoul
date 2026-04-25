package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.todokanai.busstop_seoul.R
import com.todokanai.busstop_seoul.compose.buttons.MenuButton
import com.todokanai.busstop_seoul.compose.map.MainMap
import com.todokanai.busstop_seoul.compose.map.SmallMap
import com.todokanai.busstop_seoul.compose.navigation.navigateToLineInfo
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.dataclass.StationInfo
import com.todokanai.busstop_seoul.interfaces.compose.MainMapInterface
import com.todokanai.busstop_seoul.interfaces.compose.MenuButtonInterface
import com.todokanai.busstop_seoul.viewmodel.MapViewModel

@Composable
fun MapScreen(
    navController: NavHostController,
    stId:Long? = null,
    viewModel: MapViewModel = hiltViewModel()
){

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    var targetStation by remember { mutableStateOf<StationInfo?>(null) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(viewModel.lastKnownLatLng(), viewModel.lastKnownZoomLevel())
    }

    var rangeSelectionMode by remember { mutableStateOf(false) }        // Todo: 선언 위치 조정할 것 ( 메모리 관리 )
    var rangeSelectionType by remember { mutableStateOf(1)}  //  1: 시작 지점 선택, 2: 도착 지점 선택   Todo: 선언 위치 조정할 것 ( 메모리 관리 )
    var startGroup by remember{ mutableStateOf(emptyList<Long>())}      // Todo: 선언 위치 조정할 것 ( 메모리 관리 )
    var endGroup by remember{ mutableStateOf(emptyList<Long>())}        // Todo: 선언 위치 조정할 것 ( 메모리 관리 )

    /** Todo: remember 처리 해야할지도? **/
    val mainMapInterface = object: MainMapInterface{
        override fun onCameraPositionChanged(latLngBounds: LatLngBounds) {
            viewModel.testCameraPositionChanged(latLngBounds, cameraPositionState.position.zoom)
        }

        override fun onMarkerClick(markerInfo: MarkerInfo) {
            val stationInfo = markerInfo.stationInfo
            if(rangeSelectionMode){
                if(rangeSelectionType == 1){
                    startGroup = rangeSelector(stationInfo.stId, startGroup)
                }else{
                    endGroup = rangeSelector(stationInfo.stId, endGroup)
                }
            }else {
                targetStation = stationInfo
            }
        }

        override fun markerColorSelector(stId: Long): Float {
            return if(startGroup.contains(stId)){
                BitmapDescriptorFactory.HUE_GREEN
            }else if(endGroup.contains(stId)){
                BitmapDescriptorFactory.HUE_BLUE
            }else {
                BitmapDescriptorFactory.HUE_RED
            }
        }
    }

    val menuButtonInterface = object: MenuButtonInterface{
        override fun toggleSmallMap() {
            viewModel.saveSmallMapEnabled(!uiState.value.isSmallMapEnabled)
        }
        override fun enableRotation() {
            viewModel.saveRotationGesturesEnabled(!uiState.value.rotationGesturesEnabled)
        }

        override fun toggleRangeSelectionMode() {
            if(!rangeSelectionMode){
                targetStation = null
            }                           // rangeSelectionMode 진입시 targetStation 값 null 지정
            rangeSelectionMode = !rangeSelectionMode
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        if(rangeSelectionMode){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Button(
                    onClick = {rangeSelectionType = 1},
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(R.string.range_selection_start_mode))
                }
                Button(
                    onClick = {rangeSelectionType = 2},
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = stringResource(R.string.range_selection_end_mode))
                }
            }
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

        if(targetStation != null){
            val target = targetStation!!
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
                onClose = { targetStation = null },
                toLineInfoScreen = { navController.navigateToLineInfo(it) },
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
            )
        }

        LaunchedEffect(key1 = stId){
            targetStation = viewModel.getStationInfo(stId)
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

/** startGroup, endGroup 에 targetStId 추가/제거
 *
 * @param targetStId 추가/제거할 정류소 ID
 * @param group startGroup, endGroup
 * @return 수정된 목록 **/
private fun rangeSelector(targetStId:Long, group:List<Long>):List<Long>{
    return if(group.contains(targetStId)){
        group.filter { it != targetStId }
    }else{
        group + targetStId
    }
}

// Todo: Composable parameter 를 interface 로 wrapping 하는게 나으려나?