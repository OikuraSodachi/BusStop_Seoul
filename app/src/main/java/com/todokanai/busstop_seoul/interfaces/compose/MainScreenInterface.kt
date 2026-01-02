package com.todokanai.busstop_seoul.interfaces.compose

import com.todokanai.busstop_seoul.dataclass.StationArriveInfo

/** interface for [com.todokanai.busstop_seoul.compose.MainScreen] **/
interface MainScreenInterface {

    suspend fun getArriveInfos(key:Long): List<StationArriveInfo>
    fun saveSmallMapEnabled(value: Boolean)
    fun saveRotationGesturesEnabled(value: Boolean)
}