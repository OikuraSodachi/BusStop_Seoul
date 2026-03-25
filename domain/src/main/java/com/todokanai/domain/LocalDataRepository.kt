package com.todokanai.domain

import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationItem
import kotlinx.coroutines.flow.Flow

interface LocalDataRepository {

    fun getAllStations(): Flow<List<StationItem>>

    suspend fun insertStation(stationItem: StationItem)

    suspend fun deleteStation(stationId: Long)

    suspend fun deleteAllStations()

    fun getAllBusLines() : Flow<List<BusLineItem>>

    suspend fun insertBusLine(busLineItem: BusLineItem)

    suspend fun deleteBusLine(busRouteId: Long)

    suspend fun deleteAllBusLines()

    suspend fun getAllStationItems() : List<StationItem>

    suspend fun getAllBusLineItems() : List<BusLineItem>

}