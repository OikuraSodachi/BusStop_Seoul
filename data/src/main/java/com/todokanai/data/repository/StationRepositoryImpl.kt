package com.todokanai.data.repository

import com.todokanai.data.retrofit.BusArriveRetrofit
import com.todokanai.data.retrofit.BusArriveService
import com.todokanai.domain.response.BusArrivalResponse
import com.todokanai.domain.StationRepository
import retrofit2.awaitResponse

class StationRepositoryImpl : StationRepository {

    /** Gemini Generated code **/
    override suspend fun getStationArriveInfos(key: Long): List<BusArrivalResponse> {
        return try {
            val service = BusArriveRetrofit.retrofit.create(BusArriveService::class.java)

            val response = service.getStationArrive(key.toString()).awaitResponse()

            val responseList = response.body()?.msgBody?.itemList

            responseList?.map { it.convert() } ?: emptyList()

        } catch (e: Exception) {
            println("onFailure: ${e.message}")
            emptyList()
        }
    }
}

