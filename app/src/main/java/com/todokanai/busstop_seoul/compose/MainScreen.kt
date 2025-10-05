package com.todokanai.busstop_seoul.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.todokanai.busstop_seoul.viewmodel.MainActivityUiState

@Composable
fun MainScreen(
    uiState: MainActivityUiState
){
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 10f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapLoaded = {

        }
    ) {
        Marker(
            state = remember { MarkerState(position = singapore) },
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
    }
}

//@Preview