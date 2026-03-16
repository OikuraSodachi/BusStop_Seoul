package com.todokanai.data

import com.opencsv.CSVReader

import java.io.IOException
import java.io.InputStream

class CsvManager {

    fun readCsvData(inputStream: InputStream) : List<Array<String>> {
        return try {
            val dataList = arrayListOf<Array<String>>()

            inputStream.use{
                CSVReader(it.reader()).use {
                    for (data in it) {
                        println(data)

                        dataList.add(data)
                    }
                }
            }
            dataList
        } catch (e: IOException) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
                println(e.message)
            }

            listOf()
        }
    }

//    fun toStationItem(data:Array<String>): StationItem {
//        TODO()
//    }
//
//    fun test():List<StationItem>{
//        val dataList = readCsvData("station.csv").map {
//            toStationItem(it)
//        }
//        return dataList
//    }
}