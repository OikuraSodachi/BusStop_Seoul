package com.todokanai.data.repository

import com.todokanai.data.retrofit.StationInfoRetrofit
import com.todokanai.data.retrofit.StationArriveService
import com.todokanai.data.retrofit.StationInfoService
import com.todokanai.domain.response.StationArriveItem
import com.todokanai.domain.StationRepository
import com.todokanai.domain.response.StationItem
import retrofit2.awaitResponse

class StationRepositoryImpl : StationRepository {

    /** Gemini Generated code **/
    override suspend fun getStationArriveInfos(key: Long): List<StationArriveItem> {
        return try {
            val service = StationInfoRetrofit.stationInfoRetrofit.create(StationArriveService::class.java)

            val response = service.getStationArrive(key.toString()).awaitResponse()

            val responseList = response.body()?.stationArriveMsgBody?.itemList

            responseList?.map { it.convert() } ?: emptyList()

        } catch (e: Exception) {
            println("onFailure: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getStationByName(key: String): List<StationItem> {
        return try {
            val service = StationInfoRetrofit.stationInfoRetrofit.create(StationInfoService::class.java)

            val response = service.getStationByName(key).awaitResponse()

            val responseList = response.body()?.msgBody?.itemList

            responseList?.map{it.convert()}?:emptyList()
        } catch (e: Exception) {
            println("onFailure: ${e.message}")
            emptyList()
        }
    }
}

