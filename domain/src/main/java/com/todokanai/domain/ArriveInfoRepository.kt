package com.todokanai.domain

import com.todokanai.domain.response.ArriveInfoByRouteAllItem

interface ArriveInfoRepository {

    suspend fun getArriveInfoByRouteAll(key:Long):List<ArriveInfoByRouteAllItem>
}