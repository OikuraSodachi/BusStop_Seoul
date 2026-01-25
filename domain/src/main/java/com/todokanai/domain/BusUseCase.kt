package com.todokanai.domain

import com.todokanai.domain.response.BusPositionItem
import com.todokanai.domain.response.StationArriveItem
import com.todokanai.domain.response.StationItem
import javax.inject.Inject

class BusUseCase @Inject constructor(
    private val stationRepository: StationRepository,
    private val busPositionRepository: BusPositionRepository,
    private val arriveInfoRepository: ArriveInfoRepository
) {

    suspend fun getArriveInfos(key:Long):List<StationArriveItem>{
        return stationRepository.getStationArriveInfos(key)
    }

    suspend fun getStationByName(key:String):List<StationItem>{
        return stationRepository.getStationByName(key)
    }

    suspend fun getStationByPosition(tmX: Double, tmY: Double, radius:Int):List<StationItem>{
        return stationRepository.getStationByPosition(tmX.toString(), tmY.toString(), radius.toString())
    }

    suspend fun getBusPositions(key: Long): List<BusPositionItem> {
        return busPositionRepository.getBusPositions(key)
    }
}