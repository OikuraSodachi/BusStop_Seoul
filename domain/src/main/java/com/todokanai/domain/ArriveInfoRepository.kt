package com.todokanai.domain

import com.todokanai.domain.dataclass.ArriveInfoByRouteAllItem

interface ArriveInfoRepository {

    suspend fun getArriveInfoByRouteAll(busRouteId:Long):List<ArriveInfoByRouteAllItem>
}