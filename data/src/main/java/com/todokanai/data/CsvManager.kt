package com.todokanai.data

import com.opencsv.CSVReader
import java.io.IOException
import java.io.InputStream


class CsvManager {

    fun readCsvData(inputStream: InputStream) : List<Array<String>> {
        return try {
            CSVReader(inputStream.reader()).readAll()
//            for (str in list) {
//                try {
//                    val item = StationItem(
//                        stId = str[0].toLong(),
//                        stNm = str[2],
//                        arsId = str[1].toLong(),
//                        tmX = str[3].toDouble(),
//                        tmY = str[4].toDouble(),
//                        stationTp = str[5].toIntOrNull()
//                    )
//                    itemList.add(item)
//                }catch (e:Exception){
//                    e.printStackTrace()
//                }
//            }
        } catch (e: IOException) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
            listOf()
        }
    }

//    fun toStationItem(data:Array<String>): StationItem {
//        //TODO()
//
//    }

//    fun test():List<StationItem>{
//        val dataList = readCsvData("station.csv").map {
//            toStationItem(it)
//        }
//        return dataList
//    }
}