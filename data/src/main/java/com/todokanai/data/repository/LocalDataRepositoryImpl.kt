package com.todokanai.data.repository

import android.content.res.AssetManager
import com.opencsv.CSVReader
import com.todokanai.data.BuildConfig
import com.todokanai.data.room.BusLineItemDao
import com.todokanai.data.room.StationItemDao
import com.todokanai.domain.LocalDataRepository
import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.io.InputStream
import javax.inject.Inject

class LocalDataRepositoryImpl @Inject constructor(
    private val stationItemDao: StationItemDao,
    private val busLineItemDao: BusLineItemDao,
    private val assetManager: AssetManager
) : LocalDataRepository{

    override fun getAllStations(): Flow<List<StationItem>> {
        return stationItemDao.getAll().map {
            it.mapNotNull {
                it.convert(getAllStationItems())
            }
        }
    }

    override suspend fun insertStation(stationId: Long) {
        stationItemDao.insert(
            com.todokanai.data.room.StationItem(stId = stationId)
        )
    }

    override suspend fun deleteStation(stationId: Long) {
        stationItemDao.delete(stationId)
    }

    override suspend fun deleteAllStations() {
        stationItemDao.deleteAll()
    }

    override fun getAllBusLines(): Flow<List<BusLineItem>> {
        return busLineItemDao.getAll().map {
            it.mapNotNull {
                it.convert(getAllBusLineItems())
            }
        }
    }

    override suspend fun insertBusLine(busRouteId: Long) {
        busLineItemDao.insert(
            com.todokanai.data.room.BusLineItem(busRouteId = busRouteId)
        )
    }

    override suspend fun deleteBusLine(busRouteId: Long) {
        busLineItemDao.delete(busRouteId)
    }

    override suspend fun deleteAllBusLines() {
        busLineItemDao.deleteAll()
    }

    override suspend fun getAllStationItems(): List<StationItem> {
        val inputStream = assetManager.open("SeoulBusStation.csv")
        val data = readCsvData(inputStream)
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
        val data = readCsvData(inputStream)
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

    private fun com.todokanai.data.room.StationItem.convert(infos:List<StationItem>): StationItem?{
        return infos.find { it.stId == stId }
    }

    private fun com.todokanai.data.room.BusLineItem.convert(infos:List<BusLineItem>): BusLineItem?{
        return infos.find { it.busRouteId == busRouteId }
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

    fun readCsvData(inputStream: InputStream) : List<Array<String>> {
        return try {
            CSVReader(inputStream.reader()).readAll()
        } catch (e: IOException) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
            listOf()
        }
    }
}