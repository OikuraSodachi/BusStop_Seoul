package com.todokanai.data.repository

import com.todokanai.data.retrofit.busposition.BusPositionRetrofit
import com.todokanai.data.retrofit.busposition.BusPositionService
import com.todokanai.data.retrofit.busposition.responsetype.busposbyrouteid.BusPosByRouteIdItem
import com.todokanai.domain.BusPositionRepository
import com.todokanai.domain.response.BusPositionItem
import retrofit2.awaitResponse

class BusPositionRepositoryImpl: BusPositionRepository {

    override suspend fun getBusPositions(routeId: Long): List<BusPositionItem> {
        val result = mutableListOf<BusPositionItem>()
        val service = BusPositionRetrofit.busPositionRetrofit.create(BusPositionService::class.java)
        val response = service.getBusPositions(routeId.toString()).awaitResponse()

        val responseList = response.body()?.msgBody?.itemList

        responseList?.forEach {
            try {
                result.add(it.convert())
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        return result
    }

    private fun BusPosByRouteIdItem.convert():BusPositionItem{
        return BusPositionItem(
            vehId,
            plainNo,
            busType,
            lastStnId,
            congetion,
            sectOrd,
            gpsX,
            gpsY,
            isFullFlag,
            stopFlag
        )
    }
}