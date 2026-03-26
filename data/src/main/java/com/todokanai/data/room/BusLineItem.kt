package com.todokanai.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "busLineItem")
data class BusLineItem(
    @PrimaryKey
    @ColumnInfo val busRouteId: Long
)
