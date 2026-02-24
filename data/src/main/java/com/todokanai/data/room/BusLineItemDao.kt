package com.todokanai.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusLineItemDao {

    @Query("select * from busLineItem")
    fun getAll(): Flow<List<BusLineItem>>

    @Query("select * from busLineItem")
    suspend fun getAllNonFlow(): List<BusLineItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(busLineItem: BusLineItem)

    @Query("select * from busLineItem where busRouteId = :busRouteId")
    suspend fun getBusLineById(busRouteId: String): BusLineItem

    @Query("delete from busLineItem")
    suspend fun deleteAll()

}