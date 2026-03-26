package com.todokanai.domain

import com.todokanai.domain.dataclass.ArriveInfoByRouteAllItem
import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationArriveItem
import com.todokanai.domain.dataclass.StationItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BusUseCase @Inject constructor(
    private val stationRepository: StationRepository,
    private val arriveInfoRepository: ArriveInfoRepository,
    private val localDataRepository: LocalDataRepository
) {

    var allStations = mutableListOf<StationItem>()
    var allBusLines = mutableListOf<BusLineItem>()

    init{
        CoroutineScope(Dispatchers.IO).launch {
            allStations.addAll(localDataRepository.getAllStationItems())
            allBusLines.addAll(localDataRepository.getAllBusLineItems())
        }
    }

    suspend fun getArriveInfos(arsId:Long):List<StationArriveItem>{
        return stationRepository.getStationArriveInfos(arsId)
    }

    suspend fun getStationByName(stNm:String):List<StationItem>{
        val result = mutableListOf<StationItem>()
        val allStations = allStations
        allStations.forEach {
            if(it.stNm.contains(stNm)){
                result.add(it)
            }
        }
        return result
    }

    suspend fun getStationByPosition(tmX: Double, tmY: Double, radius:Int):List<StationItem>{
        return stationRepository.getStationByPosition(tmX.toString(), tmY.toString(), radius.toString())
    }

    suspend fun getArriveInfoByRouteAll(busRouteId:Long):List<ArriveInfoByRouteAllItem>{
        return arriveInfoRepository.getArriveInfoByRouteAll(busRouteId)
    }

    suspend fun getLineInfosFromKeyWord(keyWord:String):List<BusLineItem>{
        val result = mutableListOf<BusLineItem>()
        val lineList = allBusLines  // 전체 노선 정보 가져오기

        lineList.forEach {
            if(it.rtNm.contains(keyWord)){
                result.add(it)
            }
        }
        return result
    }

    suspend fun saveBusStation(stationItem: StationItem){
        localDataRepository.insertStation(stationItem.stId)
    }

    suspend fun saveBusLine(busLineItem: BusLineItem){
        localDataRepository.insertBusLine(busLineItem.busRouteId)
    }

    suspend fun deleteBusStation(stationId: Long){
        localDataRepository.deleteStation(stationId)
    }

    suspend fun deleteBusLine(busRouteId: Long){
        localDataRepository.deleteBusLine(busRouteId)
    }

    fun getSavedBusLineItems(): Flow<List<BusLineItem>> {
        return localDataRepository.getAllBusLines()
    }

    fun getSavedStationItems():Flow<List<StationItem>>{
        return localDataRepository.getAllStations()
    }

}