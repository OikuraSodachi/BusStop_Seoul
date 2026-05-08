package com.todokanai.busstop_seoul.compose.map

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings

@Composable
fun SmallMap(
    modifier: Modifier,
    cameraPositionState: ()->CameraPositionState
){

    GoogleMap(
        modifier = modifier
            .height(300.dp)
            .width(180.dp),
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