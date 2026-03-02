package com.todokanai.domain

import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationArriveItem
import com.todokanai.domain.dataclass.StationItem

interface StationRepository {

  suspend fun getStationArriveInfos(arsId:Long): List<StationArriveItem>

  suspend fun getStationByName(stNm:String): List<StationItem>

  suspend fun getStationByPosition(tmX:String, tmY:String, radius:String): List<StationItem>

  suspend fun getRouteByStationList(arsId:Long): List<BusLineItem>

}