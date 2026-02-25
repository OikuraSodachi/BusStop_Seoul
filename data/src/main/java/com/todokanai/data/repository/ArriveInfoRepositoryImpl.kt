package com.todokanai.data.repository

import com.todokanai.data.retrofit.busarriveinfo.BusArriveInfoRetrofit
import com.todokanai.data.retrofit.busarriveinfo.BusArriveInfoService
import com.todokanai.domain.ArriveInfoRepository
import com.todokanai.domain.dataclass.ArriveInfoByRouteAllItem
import retrofit2.awaitResponse

class ArriveInfoRepositoryImpl: ArriveInfoRepository {
    override suspend fun getArriveInfoByRouteAll(busRouteId:Long): List<ArriveInfoByRouteAllItem> {
        val result = mutableListOf<ArriveInfoByRouteAllItem>()
        val service = BusArriveInfoRetrofit.busArriveInfoRetrofit.create(BusArriveInfoService::class.java)
        val response = service.getArriveInfoByRouteAll(busRouteId.toString()).awaitResponse()
        val responseList = response.body()?.msgBody?.itemList

        responseList?.forEach {
            try {
                result.add(it.convert())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }           //필수 parameter 의 nullable 제거

        return result
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