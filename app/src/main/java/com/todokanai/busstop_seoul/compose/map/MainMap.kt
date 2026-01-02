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
    markerZoomLevel:Float,   // marker 를 표시할 zoom level 최소값
    mainMapCallback: MainMapInterface
){

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapLoaded = {

        },
        uiSettings = uiSettings
    ) {
        if(cameraPositionState.position.zoom > markerZoomLevel) {       // 일정 줌 레벨 이상일 때만 마커 표시
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
        }

        if(!cameraPositionState.isMoving) {
            cameraPositionState.projection?.visibleRegion?.latLngBounds?.let{
                mainMapCallback.onVisibleRegionChanged(it)
            }
        }
    }

}