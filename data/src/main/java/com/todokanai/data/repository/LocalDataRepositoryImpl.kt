package com.todokanai.data.repository

import android.content.res.AssetManager
import com.todokanai.data.CsvManager
import com.todokanai.data.room.BusLineItemDao
import com.todokanai.data.room.StationItemDao
import com.todokanai.domain.LocalDataRepository
import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataRepositoryImpl @Inject constructor(
    private val stationItemDao: StationItemDao,
    private val busLineItemDao: BusLineItemDao,
    private val csvManager: CsvManager,
    private val assetManager: AssetManager
) : LocalDataRepository{

    override fun getAllStations(): Flow<List<StationItem>> {
        return stationItemDao.getAll().map {
            it.map {
                it.convert()
            }
        }
    }

    override suspend fun getAllStationsNonFlow(): List<StationItem> {
        return stationItemDao.getAllNonFlow().map {
            it.convert()
        }
    }

    override suspend fun insertStation(stationItem: StationItem) {
        stationItemDao.insert(stationItem.convert())
    }

    override suspend fun deleteStation(stationId: Long) {
        stationItemDao.delete(stationId)
    }

    override suspend fun deleteAllStations() {
        stationItemDao.deleteAll()
    }

    override fun getAllBusLines(): Flow<List<BusLineItem>> {
        return busLineItemDao.getAll().map {
            it.map {
                it.convert()
            }
        }
    }

    override suspend fun getAllBusLinesNonFlow(): List<BusLineItem> {
        return busLineItemDao.getAllNonFlow().map{
            it.convert()
        }
    }

    override suspend fun insertBusLine(busLineItem: BusLineItem) {
        busLineItemDao.insert(busLineItem.convert())
    }

    override suspend fun deleteBusLine(busRouteId: Long) {
        busLineItemDao.delete(busRouteId)
    }

    override suspend fun deleteAllBusLines() {
        busLineItemDao.deleteAll()
    }

    override suspend fun getAllStationItems(): List<StationItem> {
        val inputStream = assetManager.open("SeoulBusStation.csv")
        val data = csvManager.readCsvData(inputStream)
        val list = data.mapNotNull {
            try {
                convertToStationItem(it)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
        return list
    }

    override suspend fun getAllBusLineItems(): List<BusLineItem> {
        val inputStream = assetManager.open("SeoulBusLine.csv")
        val data = csvManager.readCsvData(inputStream)
        val list = data.mapNotNull {
            try {
                convertToBusLineItem(it)
            }catch (e:Exception){
                e.printStackTrace()
                null
            }
        }
        return list
    }

    private fun StationItem.convert(): com.todokanai.data.room.StationItem{
        return com.todokanai.data.room.StationItem(
            stId = stId,
            stNm = stNm,
            arsId = arsId,
            tmX = tmX,
            tmY = tmY,
            posX = posX,
            posY = posY
        )
    }

    private fun com.todokanai.data.room.StationItem.convert(): StationItem{
        return StationItem(
            stId = stId,
            stNm = stNm,
            arsId = arsId,
            tmX = tmX,
            tmY = tmY,
            posX = posX,
            posY = posY
        )
    }

    private fun BusLineItem.convert(): com.todokanai.data.room.BusLineItem{
        return com.todokanai.data.room.BusLineItem(
            busRouteId = busRouteId,
            rtNm = rtNm,
            routeAbrv = routeAbrv,
            routeType = routeType,
            stBegin = stBegin,
            stEnd = stEnd,
            term = term,
            firstBusTm = firstBusTm,
            lastBusTm = lastBusTm
        )
    }

    private fun com.todokanai.data.room.BusLineItem.convert(): BusLineItem{
        return BusLineItem(
            busRouteId = busRouteId,
            rtNm = rtNm,
            routeAbrv = routeAbrv,
            routeType = routeType,
            stBegin = stBegin,
            stEnd = stEnd,
            term = term,
            firstBusTm = firstBusTm,
            lastBusTm = lastBusTm
        )
    }

    private fun convertToStationItem(data:Array<String>): StationItem{
        return StationItem(
            stId = data[0].toLong(),
            stNm = data[2],
            arsId = data[1].toLong(),
            tmX = data[3].toDouble(),
            tmY = data[4].toDouble(),
            stationTp = data[5]
        )
    }

    private fun convertToBusLineItem(data:Array<String>):BusLineItem{
        return BusLineItem(
            busRouteId = data[0].toLong(),
            rtNm = data[1]
        )
    }
}