package com.todokanai.domain

import com.todokanai.domain.response.ArriveInfoByRouteAllItem

interface ArriveInfoRepository {

    suspend fun getArriveInfoByRouteAll(busRouteId:Long):List<ArriveInfoByRouteAllItem>
}