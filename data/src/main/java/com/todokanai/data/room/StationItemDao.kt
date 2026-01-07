package com.todokanai.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StationItemDao {

    @Query("select * from stationItem")
    fun getAll(): Flow<List<StationItem>>

    @Query("select * from stationItem")
    suspend fun getAllNonFlow(): List<StationItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stationItem: StationItem)

    @Query("select * from stationItem where stId = :stId")
    suspend fun getStationById(stId: String): StationItem

    @Query("delete from stationItem")
    suspend fun deleteAll()

}