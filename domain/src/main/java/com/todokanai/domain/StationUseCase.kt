package com.todokanai.domain

import com.todokanai.domain.response.StationArriveItem

class StationUseCase(private val stationRepository: StationRepository) {

    suspend fun getArriveInfos(key:Long):List<StationArriveItem>{
        return stationRepository.getStationArriveInfos(key)
    }
}