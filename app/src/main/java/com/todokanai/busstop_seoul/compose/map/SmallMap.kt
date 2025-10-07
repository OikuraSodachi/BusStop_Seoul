package com.todokanai.busstop_seoul.compose.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings

@Composable
fun SmallMap(
    modifier: Modifier,
    cameraPositionState: ()->CameraPositionState
){

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState(),
        onMapLoaded = {

        },
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            mapToolbarEnabled = false,
            compassEnabled = false,
            myLocationButtonEnabled = false,
            indoorLevelPickerEnabled = false,
            rotationGesturesEnabled = false,
            scrollGesturesEnabled = false,
            tiltGesturesEnabled = false,
            zoomGesturesEnabled = false
        )
    ){

    }

}