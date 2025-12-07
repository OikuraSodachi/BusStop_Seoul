package com.todokanai.domain

class StationUseCase(private val stationRepository: StationRepository) {

    suspend fun getArriveInfos(key:Long):List<BusArrivalResponse>{
        return stationRepository.getStationArriveInfos(key)
    }
}