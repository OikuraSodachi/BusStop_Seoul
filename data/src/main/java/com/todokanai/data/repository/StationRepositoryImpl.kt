package com.todokanai.data.repository

import com.todokanai.domain.ApiExplorer
import com.todokanai.domain.BusArriveRetrofit
import com.todokanai.domain.BusArriveService
import com.todokanai.domain.StationRepository
import com.todokanai.domain.stationarrrivetest.BusArrivalResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StationRepositoryImpl : StationRepository {

    override suspend fun getStationArriveInfos(key: Long): List<BusArrivalResponse> {
        CoroutineScope(Dispatchers.Default).launch {
            ApiExplorer.main(emptyArray())
        }
        val result = mutableListOf<BusArrivalResponse>()
        BusArriveRetrofit.retrofit.create(BusArriveService::class.java)
            .getStationArrive(key.toString()).enqueue(
            object : Callback<BusArrivalResponse> {
                override fun onResponse(
                    call: Call<BusArrivalResponse>,
                    response: Response<BusArrivalResponse>
                ) {

                   // println(response)

                    val temp = response.body()
                    if (temp != null) {
                        result.add(temp)
                    }
                }

                override fun onFailure(call: Call<BusArrivalResponse>, t: Throwable) {
                    println("onFailure: ${t}")
                }
            }
        )

        val temp = listOf(
            BusArrivalResponse(
                busRouteId = "Line 0"
            ),
            BusArrivalResponse(
                busRouteId = "Line 1"
            ),
            BusArrivalResponse(
                busRouteId = "Line 2"
            )
        )
        //result.addAll(temp)

        delay(3000)
        return result
    }

}