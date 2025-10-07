package com.todokanai.busstop_seoul.compose.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings

@Composable
fun SmallMap(
    modifier: Modifier,
    cameraPositionState: ()->CameraPositionState,
    uiSettings:MapUiSettings
){

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState(),
        onMapLoaded = {

        },
        uiSettings = uiSettings
    ){

    }

}