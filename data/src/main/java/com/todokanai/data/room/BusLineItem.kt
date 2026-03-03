package com.todokanai.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "busLineItem")
data class BusLineItem(
    @PrimaryKey
    @ColumnInfo val busRouteId: Long,
    @ColumnInfo val rtNm: String,
    @ColumnInfo val routeAbrv: String? = null,
    @ColumnInfo val routeType: String? = null,
    @ColumnInfo val stBegin: String? = null,
    @ColumnInfo val stEnd: String? = null,
    @ColumnInfo val term: String,
    @ColumnInfo val firstBusTm: String,
    @ColumnInfo val lastBusTm: String
)
