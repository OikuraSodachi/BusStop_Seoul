package com.todokanai.busstop_seoul.interfaces

import com.google.android.gms.maps.model.LatLngBounds
import com.todokanai.busstop_seoul.dataclass.MarkerInfo

interface MainMapCallback {

    fun onMarkerClick(markerInfo: MarkerInfo)

    fun onVisibleRegionChanged(latLngBounds: LatLngBounds)

}