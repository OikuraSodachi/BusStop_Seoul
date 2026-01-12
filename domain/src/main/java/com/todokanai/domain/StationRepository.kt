package com.todokanai.domain

import com.todokanai.domain.response.StationArriveItem
import com.todokanai.domain.response.StationItem

interface StationRepository {

  suspend fun getStationArriveInfos(key:Long): List<StationArriveItem>

  suspend fun getStationByName(key:String): List<StationItem>

  suspend fun getAllStation(): List<StationItem>

  suspend fun getStationByPosition(tmX:String, tmY:String, radius:String): List<StationItem>

}