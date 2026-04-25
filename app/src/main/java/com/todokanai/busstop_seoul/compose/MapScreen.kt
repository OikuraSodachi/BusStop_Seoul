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
import com.todokanai.busstop_seoul.compose.map.MainMap
import com.todokanai.busstop_seoul.compose.map.SmallMap
import com.todokanai.busstop_seoul.compose.navigation.navigateToLineInfo
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.dataclass.StationInfo
import com.todokanai.busstop_seoul.interfaces.compose.MainMapInterface
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

    var rangeSelectionMode by remember { mutableStateOf(false) }
    var startGroup by remember{ mutableStateOf(emptyList<Long>())}
    var endGroup by remember{ mutableStateOf(emptyList<Long>())}

    /** Todo: remember 처리 해야할지도? **/
    val mainMapInterface = object: MainMapInterface{
        override fun onCameraPositionChanged(latLngBounds: LatLngBounds) {
            viewModel.testCameraPositionChanged(latLngBounds, cameraPositionState.position.zoom)
        }

        override fun onMarkerClick(markerInfo: MarkerInfo) {
            val stationInfo = markerInfo.stationInfo
            if(rangeSelectionMode){


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

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier.weight(1f)
        ){
            MainMap(
                cameraPositionState = cameraPositionState,
                zoomControlsEnabled = uiState.value.zoomControlsEnabled,
                mapToolbarEnabled = uiState.value.mapToolbarEnabled,
                rotationGesturesEnabled = uiState.value.rotationGesturesEnabled,
                markerInfos = uiState.value.markerInfos,
                mainMapCallback = mainMapInterface
            )
            MenuButton(
                toggleSmallMap = { viewModel.saveSmallMapEnabled(!uiState.value.isSmallMapEnabled) },
                enableRotation = { viewModel.saveRotationGesturesEnabled(!uiState.value.rotationGesturesEnabled) },
                toggleRangeSelectionMode = {
                    if(!rangeSelectionMode){
                        targetStation = null
                    }                           // rangeSelectionMode 진입시 targetStation 값 null 지정
                    rangeSelectionMode = !rangeSelectionMode
                }
            )
            if (uiState.value.isSmallMapEnabled) {
                    SmallMap(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .height(300.dp)
                            .width(180.dp),
                        cameraPositionState = { smallMapCameraPositionState(cameraPositionState) }
                    ) // Todo: MainMap 과 같은 가로/세로 비율을 유지할 것
                }
        }

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