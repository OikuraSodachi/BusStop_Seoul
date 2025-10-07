package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import com.todokanai.busstop_seoul.compose.map.MainMap
import com.todokanai.busstop_seoul.compose.map.SmallMap
import com.todokanai.busstop_seoul.viewmodel.MainActivityUiState

@Composable
fun MainScreen(
    uiState: MainActivityUiState
){
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    val smallMapPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore,5f)
    }

    val isSmallMapEnabled = remember { mutableStateOf(true) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        MainMap(cameraPositionState = cameraPositionState)
        MenuButton(
            toggleSmallMap = {isSmallMapEnabled.value = !isSmallMapEnabled.value}
        )

        if (isSmallMapEnabled.value) {
            SmallMap(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .height(300.dp)
                    .width(180.dp),
                cameraPositionState = smallMapPositionState
            )
        }
    }

}