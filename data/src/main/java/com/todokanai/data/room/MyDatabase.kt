package com.todokanai.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StationItem::class, BusLineItem::class, HistoryItem::class], version = 2, exportSchema = true)
abstract class MyDatabase : RoomDatabase() {

    abstract fun stationItemDao(): StationItemDao

    abstract fun busLineItemDao(): BusLineItemDao

    abstract fun historyItemDao(): HistoryItemDao

}