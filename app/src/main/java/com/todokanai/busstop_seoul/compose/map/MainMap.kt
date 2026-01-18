package com.todokanai.busstop_seoul.compose.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.interfaces.compose.MainMapInterface

@Composable
fun MainMap(
    cameraPositionState: CameraPositionState,
    uiSettings:MapUiSettings,
    markerInfos:List<MarkerInfo>,
    mainMapCallback: MainMapInterface
){

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapLoaded = {

        },
        uiSettings = uiSettings
    ) {
        markerInfos.forEach { markerInfo ->
            Marker(
                state = MarkerState(position = markerInfo.position),
                title = markerInfo.title,
                snippet = markerInfo.snippet,
                onClick = {
                    mainMapCallback.onMarkerClick(markerInfo)
                    false
                }

            )
        }

        if(!cameraPositionState.isMoving) {
            cameraPositionState.projection?.visibleRegion?.latLngBounds?.let{
                mainMapCallback.onVisibleRegionChanged(it)
            }
        }
    }

}