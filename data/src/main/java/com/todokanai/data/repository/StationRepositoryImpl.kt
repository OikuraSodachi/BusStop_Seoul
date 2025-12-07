package com.todokanai.data.repository

import com.todokanai.data.retrofit.BusArriveRetrofit
import com.todokanai.data.retrofit.BusArriveService
import com.todokanai.domain.StationRepository
import com.todokanai.data.retrofit.stationarrrivetest.ServiceResult
import com.todokanai.domain.BusArrivalResponse
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StationRepositoryImpl : StationRepository {

    override suspend fun getStationArriveInfos(key: Long): List<BusArrivalResponse> {
        val result = mutableListOf<BusArrivalResponse>()

        BusArriveRetrofit.retrofit.create(BusArriveService::class.java)
            .getStationArrive(key.toString()).enqueue(
            object : Callback<ServiceResult> {
                override fun onResponse(
                    call: Call<ServiceResult>,
                    response: Response<ServiceResult>
                ) {

                    println(response.body())

                    val responseList = response.body()?.msgBody?.itemList
                    if (responseList != null) {
                        result.addAll(responseList.map { it.convert() })
                    }
                }

                override fun onFailure(call: Call<ServiceResult>, t: Throwable) {
                    println("onFailure: ${t}")
                }
            }
        )
        delay(3000)     // Todo: response 내용을 result 에 담은 후 return 하도록 할 것

        return result
    }

}