package com.todokanai.domain

import com.todokanai.domain.response.BusPositionItem

interface BusPositionRepository {

    suspend fun getBusPositions(routeId:Long): List<BusPositionItem>
}