package com.todokanai.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stationItem")
data class StationItem(
    @PrimaryKey             // Todo: PrimaryKey 선택이 적절한지 확인 필요
    @ColumnInfo var stId: Long          // 정류소 ID
)
