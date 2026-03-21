package com.todokanai.domain

import com.todokanai.domain.dataclass.ArriveInfoByRouteAllItem
import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationArriveItem
import com.todokanai.domain.dataclass.StationItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BusUseCase @Inject constructor(
    private val stationRepository: StationRepository,
    private val arriveInfoRepository: ArriveInfoRepository,
    private val localDataRepository: LocalDataRepository
) {

    suspend fun getArriveInfos(arsId:Long):List<StationArriveItem>{
        return stationRepository.getStationArriveInfos(arsId)
    }

    suspend fun getStationByName(stNm:String):List<StationItem>{
        return stationRepository.getStationByName(stNm)
    }

    suspend fun getStationByPosition(tmX: Double, tmY: Double, radius:Int):List<StationItem>{
        return stationRepository.getStationByPosition(tmX.toString(), tmY.toString(), radius.toString())
    }

    suspend fun getArriveInfoByRouteAll(busRouteId:Long):List<ArriveInfoByRouteAllItem>{
        return arriveInfoRepository.getArriveInfoByRouteAll(busRouteId)
    }

    suspend fun getLineInfosFromKeyWord(keyWord:String):List<BusLineItem>{
        return busLineKeyWordFilter(keyWord, localDataRepository.getAllBusLineItemsFromCsv())
    }

    fun getSavedBusLineItems(): Flow<List<BusLineItem>> {
        return localDataRepository.getAllBusLines()
    }

    fun getSavedStationItems():Flow<List<StationItem>>{
        return localDataRepository.getAllStations()
    }

    private fun busLineKeyWordFilter(keyWord:String, lines:List<BusLineItem>):List<BusLineItem>{
        val result = mutableListOf<BusLineItem>()
        lines.forEach {
            if(it.rtNm.contains(keyWord)){
                result.add(it)
            }
        }
        return result
    }

}