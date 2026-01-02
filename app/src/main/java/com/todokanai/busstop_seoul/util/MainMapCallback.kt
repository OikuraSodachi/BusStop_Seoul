package com.todokanai.busstop_seoul.util

import com.google.android.gms.maps.model.LatLngBounds
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.compose.interfaces.MainMapInterface

class MainMapCallback: MainMapInterface {

    override fun onMarkerClick(markerInfo: MarkerInfo) {
        println("onMarkerClick")
    }

    override fun onVisibleRegionChanged(latLngBounds: LatLngBounds) {
        println("onVisibleRegionChanged: ${latLngBounds}")
    }

}