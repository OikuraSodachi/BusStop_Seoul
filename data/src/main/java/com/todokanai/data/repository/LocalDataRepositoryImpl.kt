package com.todokanai.data.repository

import com.todokanai.data.room.StationItemDao
import com.todokanai.domain.LocalDataRepository
import com.todokanai.domain.response.StationItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataRepositoryImpl @Inject constructor(private val stationItemDao: StationItemDao) : LocalDataRepository{

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

    override suspend fun deleteAllStations() {
        stationItemDao.deleteAll()
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

}