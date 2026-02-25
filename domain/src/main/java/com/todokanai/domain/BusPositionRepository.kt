package com.todokanai.domain

import com.todokanai.domain.dataclass.BusPositionItem

interface BusPositionRepository {

    suspend fun getBusPositions(routeId:Long): List<BusPositionItem>
}