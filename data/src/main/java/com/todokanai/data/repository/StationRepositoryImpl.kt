package com.todokanai.data.repository

import com.todokanai.domain.BusStationTest
import com.todokanai.domain.LineArriveRetrofit
import com.todokanai.domain.LineArriveServiceTest
import com.todokanai.domain.StationRepository
import com.todokanai.domain.dataclass.StationArriveInfo_temp
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StationRepositoryImpl : StationRepository {
    override suspend fun getStationByName(keyWord:String): List<BusStationTest> {
        val result = mutableListOf<BusStationTest>()
        //BusArriveRetrofit.retrofit.create()
        return result
    }

    override suspend fun getStationArriveInfos(key: Long): List<StationArriveInfo_temp> {
        val result = mutableListOf<StationArriveInfo_temp>()
      //  println("key: $key")
        LineArriveRetrofit.retrofit.create(LineArriveServiceTest::class.java)
            .getStationArrive(key.toString()).enqueue(
            object : Callback<> {
                override fun onResponse(
                    call: Call<>,
                    response: Response<>
                ) {
                    println(response)
                    //println("onResponse: ${response.body()?.ServiceResult?.msgBody}")
                }

                override fun onFailure(call: Call<>, t: Throwable) {
                    println("onFailure: ${t}")
                }
            }
        )

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