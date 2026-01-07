package com.todokanai.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StationItem::class], version = 1,exportSchema = true)
abstract class MyDatabase : RoomDatabase() {

    abstract fun stationItemDao(): StationItemDao

}