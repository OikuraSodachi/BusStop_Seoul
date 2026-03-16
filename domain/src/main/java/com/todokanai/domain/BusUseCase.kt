package com.todokanai.domain

import com.todokanai.domain.dataclass.ArriveInfoByRouteAllItem
import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.BusPositionItem
import com.todokanai.domain.dataclass.StationArriveItem
import com.todokanai.domain.dataclass.StationItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BusUseCase @Inject constructor(
    private val stationRepository: StationRepository,
    private val busPositionRepository: BusPositionRepository,
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

    suspend fun getBusPositions(routeId: Long): List<BusPositionItem> {
        return busPositionRepository.getBusPositions(routeId)
    }

    suspend fun getArriveInfoByRouteAll(busRouteId:Long):List<ArriveInfoByRouteAllItem>{
        return arriveInfoRepository.getArriveInfoByRouteAll(busRouteId)
    }

    suspend fun getLineInfosFromKeyWord(keyWord:String):List<BusLineItem>{
        return busLineKeyWordFilter(keyWord, localDataRepository.getAllBusLineItems())
    }

    fun getSavedBusLineItems(): Flow<List<BusLineItem>> {
        return localDataRepository.getAllBusLines()
    }

    suspend fun getSavedBusLineItemsNonFlow():List<BusLineItem>{
        return localDataRepository.getAllBusLinesNonFlow()
    }

    fun getSavedStationItems():Flow<List<StationItem>>{
        return localDataRepository.getAllStations()
    }

    suspend fun getSavedStationItemsNonFlow():List<StationItem>{
        return localDataRepository.getAllStationsNonFlow()
    }

    suspend fun saveBusLineItem(busLine: BusLineItem){
        localDataRepository.insertBusLine(busLine)
    }

    suspend fun saveStationItem(station: StationItem){
        localDataRepository.insertStation(station)
    }

    suspend fun deleteBusLineItem(busRouteId:Long){
        localDataRepository.deleteBusLine(busRouteId)
    }

    suspend fun deleteStationItem(stationId:Long){
        localDataRepository.deleteStation(stationId)
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