package com.todokanai.domain

import com.todokanai.domain.response.BusArrivalResponse

interface StationRepository {

  suspend fun getStationArriveInfos(key:Long): List<BusArrivalResponse>

}