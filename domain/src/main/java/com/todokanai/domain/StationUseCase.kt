package com.todokanai.domain

import com.todokanai.domain.response.StationArriveItem
import com.todokanai.domain.response.StationItem

class StationUseCase(private val stationRepository: StationRepository) {

    suspend fun getArriveInfos(key:Long):List<StationArriveItem>{
        return stationRepository.getStationArriveInfos(key)
    }

    suspend fun getStationByName(key:String):List<StationItem>{
        return stationRepository.getStationByName(key)
    }

    suspend fun getStationByPosition(tmX: Double, tmY: Double, radius:Int):List<StationItem>{
        return stationRepository.getStationByPosition(tmX.toString(), tmY.toString(), radius.toString())
    }

}