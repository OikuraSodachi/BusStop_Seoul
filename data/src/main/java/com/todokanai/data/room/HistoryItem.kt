package com.todokanai.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "historyItem", primaryKeys = ["id", "type"])
data class HistoryItem(
    @ColumnInfo val id: Long,
    @ColumnInfo val type: Int,       // 0 = LINE, 1 = STATION
    @ColumnInfo val timestamp: Long
)
