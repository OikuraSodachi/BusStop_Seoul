package com.todokanai.domain

interface StationRepository {

  suspend fun getStationByName(keyWord:String):List<BusStationTest>

}