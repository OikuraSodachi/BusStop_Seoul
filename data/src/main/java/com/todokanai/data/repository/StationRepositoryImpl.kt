package com.todokanai.data.repository

import android.util.Log
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
            Log.d("${this.javaClass}"+".getStationArriveInfos","onFailure: ${e.message}")
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
            Log.d("${this.javaClass}"+".getStationByName","onFailure: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getStationByPosition(
        tmX: String,
        tmY: String,
        radius: String
    ): List<StationItem> {
        return try {
            val service = StationInfoRetrofit.stationInfoRetrofit.create(StationInfoService::class.java)

            val testX = "126.9161669371"
            val testY = "37.5606439736"
            val testRadius = "50"
            val response = service.getStationByPosition(tmX, tmY, radius).awaitResponse()
            //val response = service.getStationByPosition(testX, testY, testRadius).awaitResponse()

            println("test: ${response.raw()}")
            val responseList = response.body()?.msgBody?.itemList

//            println("size: ${responseList?.size}")
//            println("list: ${responseList}")
            responseList?.map{it.convert()}?:emptyList()
        } catch (e: Exception) {
            Log.d("${this.javaClass}"+".getStationByName","onFailure: ${e.message}")
            emptyList()
        }
    }

    /** simpleXML Converter 관련 annotation 제거
     *  @return [com.todokanai.domain.response.StationArriveItem] **/
    private fun com.todokanai.data.retrofit.stationarrive.StationArriveItem.convert(): StationArriveItem {
        return StationArriveItem(
            stId,
            stNm,
            arsId,
            busRouteId,
            rtNm,
            busRouteAbrv,
            sectNm,
            gpsX,
            gpsY,
            stationTp,
            firstTm,
            lastTm,
            term,
            routeType,
            nextBus,
            staOrd,
            vehId1,
            sectOrd1,
            stationNm1,
            traTime1,
            traSpd1,
            isArrive1,
            repTm1,
            isLast1,
            busType1,
            vehId2,
            sectOrd2,
            stationNm2,
            traTime2,
            traSpd2,
            isArrive2,
            isLast2,
            busType2,
            adirection,
            arrmsg1,
            arrmsg2,
            arrmsgSec1,
            arrmsgSec2,
            nxtStn,
            rerdieDiv1,
            rerdieDiv2,
            rerideNum1,
            rerideNum2,
            isFullFlag1,
            isFullFlag2,
            deTourAt,
            congestion1,
            congestion2,
            remndrNmpr1,
            remndrNmpr2
        )
    }

    private fun com.todokanai.data.retrofit.stationbyname.StationItem.convert(): StationItem {
        return StationItem(
            stId,
            stNm,
            arsId,
            tmX?.toDouble(),
            tmY?.toDouble(),
            posX,
            posY
        )
    }

}

