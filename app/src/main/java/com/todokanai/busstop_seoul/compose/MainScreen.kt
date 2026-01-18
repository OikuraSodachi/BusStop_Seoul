package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import com.todokanai.busstop_seoul.compose.map.MainMap
import com.todokanai.busstop_seoul.compose.map.SmallMap
import com.todokanai.busstop_seoul.interfaces.compose.MainMapInterface
import com.todokanai.busstop_seoul.interfaces.compose.MainScreenInterface
import com.todokanai.busstop_seoul.viewmodel.MainActivityUiState

@Composable
fun MainScreen(
    uiState: MainActivityUiState,
    mainMapCallback: MainMapInterface,
    mainScreenInterface: MainScreenInterface
){

    val seoul =  LatLng(37.532600, 127.024612)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(seoul, 10f)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier.weight(1f)
        ){
            MainMap(
                cameraPositionState = cameraPositionState,
                uiSettings = uiState.mapUiSettings,   // Todo: uiSettings 값 변경에 따른 Recomposition 검증 필요
                markerInfos = uiState.markerInfos,
                mainMapCallback = mainMapCallback
            )
            MenuButton(
                toggleSmallMap = { mainScreenInterface.saveSmallMapEnabled(!uiState.isSmallMapEnabled) },
                enableRotation = {mainScreenInterface.saveRotationGesturesEnabled(!uiState.mapUiSettings.rotationGesturesEnabled) }
            )
            if (uiState.isSmallMapEnabled) {
                    SmallMap(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .height(300.dp)
                            .width(180.dp),
                        cameraPositionState = {
                            CameraPositionState(
                                position = CameraPosition(
                                    cameraPositionState.position.target,
                                    cameraPositionState.position.zoom / 2,
                                    cameraPositionState.position.tilt,
                                    cameraPositionState.position.bearing
                                )
                            )
                        },
                        uiSettings = uiState.smallMapSettings
                    ) // Todo: MainMap 과 같은 가로/세로 비율을 유지할 것
                }
        }

        if(uiState.targetStationId != null){
            StationInfoScreen(
                stationId = uiState.targetStationId,
                getArriveInfos = {mainScreenInterface.getArriveInfos(it)},
                onClose = { mainScreenInterface.invalidateTargetStation() },
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            )
        }
    }

}