package com.todokanai.data.repository

import com.todokanai.domain.BusStationTest
import com.todokanai.domain.StationRepository
import com.todokanai.domain.dataclass.StationArriveInfo_temp
import kotlinx.coroutines.delay

class StationRepositoryImpl : StationRepository {
    override suspend fun getStationByName(keyWord:String): List<BusStationTest> {
        val result = mutableListOf<BusStationTest>()
        //BusArriveRetrofit.retrofit.create()
        return result
    }

    override suspend fun getStationArriveInfos(key: Long): List<StationArriveInfo_temp> {
        val result = mutableListOf<StationArriveInfo_temp>()

        val temp = listOf(
            StationArriveInfo_temp(
                id = 0,
                lineNumber = "Line 0",
                estTime = 0
            ),
            StationArriveInfo_temp(
                id = 1,
                lineNumber = "Line 1",
                estTime = 11111
            ),
            StationArriveInfo_temp(
                id = 2,
                lineNumber = "Line 2",
                estTime = 22222
            )

        )
        result.addAll(temp)

        delay(3000)
        return result
    }

}