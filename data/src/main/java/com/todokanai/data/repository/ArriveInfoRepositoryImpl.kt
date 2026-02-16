package com.todokanai.data.repository

import android.util.Log
import com.todokanai.data.retrofit.busarriveinfo.BusArriveInfoRetrofit
import com.todokanai.data.retrofit.busarriveinfo.BusArriveInfoService
import com.todokanai.domain.ArriveInfoRepository
import com.todokanai.domain.response.ArriveInfoByRouteAllItem
import retrofit2.awaitResponse

class ArriveInfoRepositoryImpl: ArriveInfoRepository {
    override suspend fun getArriveInfoByRouteAll(key:Long): List<ArriveInfoByRouteAllItem> {
        return try {
            val service = BusArriveInfoRetrofit.busArriveInfoRetrofit.create(BusArriveInfoService::class.java)

            val response = service.getArriveInfoByRouteAll(key.toString()).awaitResponse()

            val responseList = response.body()?.msgBody?.itemList

            println("responseList: ${responseList}")

            val result = mutableListOf<ArriveInfoByRouteAllItem>()

            responseList?.forEach {
                try {
                    result.add(it.convert())
                }catch (e:Exception){
                     e.printStackTrace()
                }
            }           //필수 parameter 의 nullable 제거

            result

        } catch (e: Exception) {
            Log.d("${this.javaClass}"+".getArriveInfoByRouteAll","onFailure: ${e.message}")
            emptyList()

        }
    }

    private fun com.todokanai.data.retrofit.busarriveinfo.responsetype.arrinfobyrouteall.ArriveInfoByRouteAllItem.convert(): ArriveInfoByRouteAllItem{
        return ArriveInfoByRouteAllItem(
            stId!!.toLong(),
            stNm!!,
            arsId!!.toLong(),
            staOrd!!,
            busRouteId!!.toLong(),
            rtNm!!,
            arrmsg1,
            vehId1,
            busType1,
            plainNo1,
            arrmsg2,
            vehId2,
            busType2,
            plainNo2,
            mkTm,
            routeType,
            term,
            firstTm,
            lastTm
        )
    }
}