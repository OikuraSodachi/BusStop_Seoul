package com.todokanai.domain

import com.todokanai.domain.dataclass.StationArriveInfo_temp

class StationUseCase(private val stationRepository: StationRepository) {

    suspend fun getArriveInfos(key:Long):List<StationArriveInfo_temp>{
        return stationRepository.getStationArriveInfos(key)
    }
}