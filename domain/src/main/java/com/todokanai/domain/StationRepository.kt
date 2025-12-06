package com.todokanai.domain

import com.todokanai.domain.stationarrrivetest.BusArrivalResponse

interface StationRepository {

  suspend fun getStationArriveInfos(key:Long): List<BusArrivalResponse>

}