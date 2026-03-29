package com.todokanai.busstop_seoul.interfaces.compose

import com.google.maps.android.compose.CameraPositionState

/** interface for [com.todokanai.busstop_seoul.compose.map.MainMap] **/
interface MainMapInterface {

    fun onCameraPositionChanged(cameraPositionState: CameraPositionState)

}