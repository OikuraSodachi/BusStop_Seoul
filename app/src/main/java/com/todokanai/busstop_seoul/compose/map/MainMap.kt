package com.todokanai.busstop_seoul.compose.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.interfaces.compose.MainMapInterface

/** GoogleMap 부분의 구체적 내용만 남겼음. 더이상  MainMap 건들지 말기
 *
 * [MarkerInfo] 의 구조 변경에 따른 refactor 는 예외 **/
@Composable
fun MainMap(
    cameraPositionState: CameraPositionState,
    zoomControlsEnabled:Boolean,
    mapToolbarEnabled:Boolean,
    rotationGesturesEnabled:Boolean,
    markerInfos:List<MarkerInfo>,
    mainMapCallback: MainMapInterface
){

    val uiSettings = remember(zoomControlsEnabled, mapToolbarEnabled, rotationGesturesEnabled) {
        MapUiSettings(
            zoomControlsEnabled = zoomControlsEnabled,
            mapToolbarEnabled = mapToolbarEnabled,
            rotationGesturesEnabled = rotationGesturesEnabled
        )
    }

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
                icon = BitmapDescriptorFactory.defaultMarker(mainMapCallback.markerColorSelector(markerInfo.stationInfo.stId)),
                onClick = {
                    mainMapCallback.onMarkerClick(markerInfo)
                    false
                }

            )
        }

        if(!cameraPositionState.isMoving) {
            val latLngBounds = cameraPositionState.projection?.visibleRegion?.latLngBounds
            latLngBounds?.let {
                mainMapCallback.onCameraPositionChanged(it)
            }
        }
    }

}