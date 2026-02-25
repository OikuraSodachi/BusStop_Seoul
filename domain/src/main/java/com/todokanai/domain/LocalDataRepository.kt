package com.todokanai.domain

import com.todokanai.domain.response.StationItem
import kotlinx.coroutines.flow.Flow

interface LocalDataRepository {

    fun getAllStations(): Flow<List<StationItem>>

    suspend fun getAllStationsNonFlow(): List<StationItem>

    suspend fun insertStation(stationItem: StationItem)

    suspend fun deleteAllStations()
}