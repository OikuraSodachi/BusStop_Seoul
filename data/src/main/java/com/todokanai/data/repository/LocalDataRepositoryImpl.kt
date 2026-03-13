package com.todokanai.data.repository

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
    private val busLineItemDao: BusLineItemDao
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
}