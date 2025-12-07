package com.todokanai.domain

interface StationRepository {

  suspend fun getStationArriveInfos(key:Long): List<BusArrivalResponse>

}