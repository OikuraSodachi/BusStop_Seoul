package com.todokanai.busstop_seoul.interfaces.compose

import com.todokanai.busstop_seoul.dataclass.LineInfo
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo
import com.todokanai.busstop_seoul.dataclass.StationInfo

/** interface for [com.todokanai.busstop_seoul.compose.MainScreen] **/
interface MainScreenInterface {

    suspend fun getArriveInfos(key:Long): List<StationArriveInfo>
    suspend fun getVisibleStation(tmX:Double, tmY:Double, radius:Int):List<StationInfo>
    suspend fun getLineInfos(routeId:Long):List<LineInfo>
    fun saveSmallMapEnabled(value: Boolean)
    fun saveRotationGesturesEnabled(value: Boolean)
    fun invalidateTargetStation()
}