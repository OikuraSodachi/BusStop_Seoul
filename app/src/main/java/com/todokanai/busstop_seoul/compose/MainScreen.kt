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
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState
import com.todokanai.busstop_seoul.compose.map.MainMap
import com.todokanai.busstop_seoul.compose.map.SmallMap
import com.todokanai.busstop_seoul.compose.navigation.navigateToLineInfo
import com.todokanai.busstop_seoul.compose.navigation.navigateToSearchScreen
import com.todokanai.busstop_seoul.dataclass.StationInfo
import com.todokanai.busstop_seoul.viewmodel.MainViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    arsId:Long? = null,
    stNm:String?= null,
    viewModel: MainViewModel = hiltViewModel()
){

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    var targetStation by remember { mutableStateOf<StationInfo?>(null) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(37.532600, 127.024612), 10f)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier.weight(1f)
        ){
            MainMap(
                cameraPositionState = cameraPositionState,
                uiSettings = uiState.value.mapUiSettings,   // Todo: uiSettings 값 변경에 따른 Recomposition 검증 필요
                markerInfos = uiState.value.markerInfos,
                mainMapCallback = viewModel.mainMapCallback,
                onMarkerClick = {
                    targetStation = it.stationInfo
                }
            )
            MenuButton(
                toggleSmallMap = { viewModel.saveSmallMapEnabled(!uiState.value.isSmallMapEnabled) },
                enableRotation = { viewModel.saveRotationGesturesEnabled(!uiState.value.mapUiSettings.rotationGesturesEnabled) },
                toSearchScreen = { navController.navigateToSearchScreen() }
            )
            if (uiState.value.isSmallMapEnabled) {
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
                        uiSettings = uiState.value.smallMapSettings
                    ) // Todo: MainMap 과 같은 가로/세로 비율을 유지할 것
                }
        }

        if(targetStation != null){
            val target = targetStation!!
            cameraPositionState.position =
                CameraPosition.fromLatLngZoom(
                    LatLng(target.tmY, target.tmX),
                    15f
                )
            StationInfoScreen(
                arsId = target.arsId,
                stName = target.stNm,
                getArriveInfos =  {viewModel.getArriveInfos(it)},
                onClose = { targetStation = null },
                toLineInfoScreen = { navController.navigateToLineInfo(it) },
                modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
            )
        }

        LaunchedEffect(key1 = arsId, key2= stNm){
            if(arsId!=null && stNm!=null) {
                targetStation = viewModel.getStationInfo(arsId, stNm)
            }
        }

    }

}