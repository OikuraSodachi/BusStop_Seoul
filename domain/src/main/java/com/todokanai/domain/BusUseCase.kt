package com.todokanai.domain

import com.todokanai.domain.dataclass.ArriveInfoByRouteAllItem
import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.BusPositionItem
import com.todokanai.domain.dataclass.StationArriveItem
import com.todokanai.domain.dataclass.StationItem
import javax.inject.Inject

class BusUseCase @Inject constructor(
    private val stationRepository: StationRepository,
    private val busPositionRepository: BusPositionRepository,
    private val arriveInfoRepository: ArriveInfoRepository,
    private val localDataRepository: LocalDataRepository
) {

    suspend fun getArriveInfos(arsId:Long):List<StationArriveItem>{
        val result = mutableListOf<StationArriveItem>()

        val lineList = stationRepository.getRouteByStationList(arsId)
        lineList.forEach {
            result.add(
                StationArriveItem(
                    stId = 0,
                    stNm = "",
                    arsId = arsId,
                    busRouteId = it.busRouteId,
                    rtNm = it.rtNm,
                    adirection = ""
                )       // Todo: UseCase 단에 convert 로직을 두는게 맞는지?
            )
        }

        return stationRepository.getStationArriveInfos(arsId)
    }

    suspend fun getStationByName(stNm:String):List<StationItem>{
        val response = stationRepository.getStationByName(stNm)
        return response
    }

    suspend fun getStationByPosition(tmX: Double, tmY: Double, radius:Int):List<StationItem>{
        val response = stationRepository.getStationByPosition(tmX.toString(), tmY.toString(), radius.toString())
        return response
    }

    suspend fun getBusPositions(routeId: Long): List<BusPositionItem> {
        return busPositionRepository.getBusPositions(routeId)
    }

    suspend fun getArriveInfoByRouteAll(busRouteId:Long):List<ArriveInfoByRouteAllItem>{
        return arriveInfoRepository.getArriveInfoByRouteAll(busRouteId)

    }

    suspend fun getLineInfosFromKeyWord(keyWord:String):List<BusLineItem>{
        val result = mutableListOf<BusLineItem>()
        val lineList = localDataRepository.getAllBusLinesNonFlow()  // 전체 노선 정보 가져오기

        lineList.forEach {
            if(it.rtNm.contains(keyWord)){
                result.add(it)
            }
        }
        return result
    }

}