package com.todokanai.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryItemDao {

    @Query("select * from historyItem where type = :type order by timestamp desc")
    fun getAllByType(type: Int): Flow<List<HistoryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(historyItem: HistoryItem)

    @Query("delete from historyItem where id = :id and type = :type")
    suspend fun delete(id: Long, type: Int)

    @Query("delete from historyItem")
    suspend fun deleteAll()

}
