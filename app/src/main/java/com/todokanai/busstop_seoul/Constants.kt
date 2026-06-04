package com.todokanai.busstop_seoul

object Constants {
    const val MAP_MARKER_MINIMUM_RADIUS : Int = 2000    // GoogleMap Marker 를 표시하기 위한 축척 배율 조건값
    const val ZOOM_ON_MARKER_CLICK : Float = 18f
    const val DEFAULT_LATITUDE : Double = 37.532600
    const val DEFAULT_LONGITUDE : Double = 127.024612
    const val DEFAULT_ZOOM : Float = 10f

    const val MAP_SCREEN_ROUTE : String = "MapScreen"
    const val STATION_ID_ARG : String = "stIdArg"

    const val SEARCH_SCREEN_ROUTE : String = "SearchScreen"

    const val LINE_INFO_SCREEN_ROUTE : String = "LineInfoScreen"
    const val LINE_INFO_ARG : String = "lineInfoArg"
    const val TARGET_STATION_ID_ARG : String = "targetStationIdArg"

    const val MAIN_SCREEN_ROUTE : String = "MainScreen"
}