package com.todokanai.domain

import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationItem
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val localDataRepository: LocalDataRepository
){

    suspend fun getStationByName(stNm:String):List<StationItem>{
        return localDataRepository.getAllStationItems().filter{ it.stNm.contains(stNm) }
    }

    suspend fun getLineInfosFromKeyWord(keyWord:String):List<BusLineItem> {
        return localDataRepository.getAllBusLineItems().filter{ it.rtNm.contains(keyWord) }.distinctBy { it.busRouteId }
    }

}