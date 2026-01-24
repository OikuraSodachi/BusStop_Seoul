package com.todokanai.domain

import com.todokanai.domain.response.BusPositionItem

interface BusPositionRepository {

    suspend fun getBusPositions(key:Long): List<BusPositionItem>
}