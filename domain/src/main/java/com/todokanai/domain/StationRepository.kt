package com.todokanai.domain

interface StationRepository {

  //  suspend fun getStationByNameList(name: String): List<StationByNameList>
  fun getBusArrive(nodId:String)

}