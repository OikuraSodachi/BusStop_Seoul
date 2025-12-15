package com.todokanai.domain

import com.todokanai.domain.response.BusArrivalResponse

class StationUseCase(private val stationRepository: StationRepository) {

    suspend fun getArriveInfos(key:Long):List<BusArrivalResponse>{
        return stationRepository.getStationArriveInfos(key)
    }
}