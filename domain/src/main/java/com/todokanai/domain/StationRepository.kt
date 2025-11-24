package com.todokanai.domain

import com.todokanai.domain.dataclass.StationArriveInfo_temp

interface StationRepository {

  suspend fun getStationByName(keyWord:String):List<BusStationTest>

  suspend fun getStationArriveInfos(key:Long): List<StationArriveInfo_temp>

}