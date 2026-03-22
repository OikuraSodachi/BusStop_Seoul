package com.todokanai.domain

import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationItem
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface LocalDataRepository {

    fun getAllStations(): Flow<List<StationItem>>

    suspend fun getAllStationsNonFlow(): List<StationItem>

    suspend fun insertStation(stationItem: StationItem)

    suspend fun deleteStation(stationId: Long)

    suspend fun deleteAllStations()

    fun getAllBusLines() : Flow<List<BusLineItem>>

    suspend fun getAllBusLinesNonFlow() : List<BusLineItem>

    suspend fun insertBusLine(busLineItem: BusLineItem)

    suspend fun deleteBusLine(busRouteId: Long)

    suspend fun deleteAllBusLines()

//    suspend fun readCsvData(inputStream: InputStream) : List<Array<String>>

    suspend fun getAllStationItems(inputStream: InputStream) : List<StationItem>

    suspend fun getAllBusLineItems(inputStream: InputStream) : List<BusLineItem>

}