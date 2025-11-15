package com.todokanai.data.repository

import com.todokanai.domain.BusStationTest
import com.todokanai.domain.StationRepository

class StationRepositoryImpl : StationRepository {
    override suspend fun getStationByName(keyWord:String): List<BusStationTest> {
        val result = mutableListOf<BusStationTest>()
        //BusArriveRetrofit.retrofit.create()
        return result
    }

}