package com.todokanai.domain

import com.todokanai.domain.response.BusPositionItem
import javax.inject.Inject

class BusPositionUseCase @Inject constructor(
    private val busPositionRepository: BusPositionRepository
) {
    suspend fun getBusPositions(key: Long): List<BusPositionItem> {
        return busPositionRepository.getBusPositions(key)
    }

}