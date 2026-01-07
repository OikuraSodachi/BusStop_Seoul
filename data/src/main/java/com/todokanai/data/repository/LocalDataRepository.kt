package com.todokanai.data.repository

import com.todokanai.data.room.StationItem
import com.todokanai.data.room.StationItemDao
import javax.inject.Inject

/** Todo: RepositoryImpl 방식을 따를 것인지 고민해볼 것 **/
class LocalDataRepository @Inject constructor(private val stationItemDao: StationItemDao) {

    fun getAll() = stationItemDao.getAll()

    suspend fun getAllNonFlow() = stationItemDao.getAllNonFlow()

    suspend fun insert(stationItem: StationItem) = stationItemDao.insert(stationItem)

    suspend fun deleteAll() = stationItemDao.deleteAll()




}