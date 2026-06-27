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

    suspend fun getStationById(stId:Long):StationItem?{
        return localDataRepository.getAllStationItems().find{it.stId == stId}
    }

    suspend fun getStationByPosition(tmX: Double, tmY: Double, radius:Int):List<StationItem>{
        return stationRepository.getStationByPosition(tmX.toString(), tmY.toString(), radius.toString())
    }

    suspend fun getArriveInfoByRouteAll(busRouteId:Long):List<ArriveInfoByRouteAllItem>{
        return arriveInfoRepository.getArriveInfoByRouteAll(busRouteId)
    }

    suspend fun getBusPositions(routeId:Long):List<BusPositionItem>{
        return busPositionRepository.getBusPositions(routeId)
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

    fun getHistoryStations(): Flow<List<StationItem>> = localDataRepository.getHistoryStations()

    fun getHistoryLines(): Flow<List<BusLineItem>> = localDataRepository.getHistoryLines()

    suspend fun saveStationToHistory(stationId: Long) = localDataRepository.insertHistoryStation(stationId)

    suspend fun saveLineToHistory(busRouteId: Long) = localDataRepository.insertHistoryLine(busRouteId)

    /** @param startArsIds 출발 정류소 ID 목록
     * @param endArsIds 도착 정류소 ID 목록
     * @return 공통 노선 목록
     * Todo: ( [startArsIds].size + [endArsIds].size ) 만큼 API 호출이 실행됨. 줄일 방법이 있는지? **/
    suspend fun getRangeSearchResult(startArsIds:List<Long>, endArsIds:List<Long>):List<BusLineItem>{
        val startGroup = mutableListOf<BusLineItem>()
        val endGroup = mutableListOf<BusLineItem>()

        startArsIds.forEach {
            startGroup.addAll(stationRepository.getRouteByStationList(it))
        }
        endArsIds.forEach{
            endGroup.addAll(stationRepository.getRouteByStationList(it))
        }

        val result = startGroup.union(endGroup)
        return result.toList()
    }

}