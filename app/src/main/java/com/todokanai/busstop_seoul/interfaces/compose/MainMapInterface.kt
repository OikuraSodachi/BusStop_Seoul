package com.todokanai.busstop_seoul.interfaces.compose

import com.google.android.gms.maps.model.LatLngBounds
import com.todokanai.busstop_seoul.dataclass.MarkerInfo

/** interface for [com.todokanai.busstop_seoul.compose.map.MainMap] **/
interface MainMapInterface {

    fun onCameraPositionChanged(latLngBounds: LatLngBounds)

    fun onMarkerClick(markerInfo: MarkerInfo)
}