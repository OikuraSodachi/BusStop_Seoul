package com.todokanai.busstop_seoul.compose.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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
    zoomControlsEnabled:Boolean,
    mapToolbarEnabled:Boolean,
    rotationGesturesEnabled:Boolean,
    markerInfos:List<MarkerInfo>,
    mainMapCallback: MainMapInterface
){

    var startGroup by remember{ mutableStateOf(emptyList<Long>())}
    var endGroup by remember{ mutableStateOf(emptyList<Long>())}

    /**
     * @param stId 정류소 ID
     * @return color for the marker **/
    fun markerColorSelector(stId:Long):Float{
        return if(startGroup.contains(stId)){
            BitmapDescriptorFactory.HUE_GREEN
        }else if(endGroup.contains(stId)){
            BitmapDescriptorFactory.HUE_BLUE
        }else {
            BitmapDescriptorFactory.HUE_RED
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapLoaded = {

        },
        uiSettings = MapUiSettings(
            zoomControlsEnabled = zoomControlsEnabled,
            mapToolbarEnabled = mapToolbarEnabled,
            rotationGesturesEnabled = rotationGesturesEnabled
        )
    ) {
        markerInfos.forEach { markerInfo ->
            Marker(
                state = MarkerState(position = markerInfo.position),
                title = markerInfo.title,
                snippet = markerInfo.snippet,
                icon = BitmapDescriptorFactory.defaultMarker(markerColorSelector(markerInfo.stationInfo.stId)),
                onClick = {
                    mainMapCallback.onMarkerClick(markerInfo)
                    println(markerInfo.stationInfo.stId)
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