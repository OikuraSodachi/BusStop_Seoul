package com.todokanai.domain

import com.todokanai.domain.response.StationArriveItem
import com.todokanai.domain.response.StationItem

interface StationRepository {

  suspend fun getStationArriveInfos(arsId:Long): List<StationArriveItem>

  suspend fun getStationByName(stNm:String): List<StationItem>

  suspend fun getStationByPosition(tmX:String, tmY:String, radius:String): List<StationItem>

}