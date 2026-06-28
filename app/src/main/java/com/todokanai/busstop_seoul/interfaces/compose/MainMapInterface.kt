package com.todokanai.busstop_seoul.interfaces.compose

import androidx.compose.runtime.Stable
import com.google.android.gms.maps.model.LatLngBounds
import com.todokanai.busstop_seoul.dataclass.MarkerInfo

/** interface for [com.todokanai.busstop_seoul.compose.map.MainMap] **/
@Stable
interface MainMapInterface {

    fun onCameraPositionChanged(latLngBounds: LatLngBounds)

    fun onMarkerClick(markerInfo: MarkerInfo)

    fun markerColorSelector(stId:Long):Float
}