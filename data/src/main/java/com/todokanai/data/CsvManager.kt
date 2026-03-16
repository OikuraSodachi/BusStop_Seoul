package com.todokanai.data

import com.opencsv.CSVReader
import java.io.IOException
import java.io.InputStream


class CsvManager {

    fun readCsvData(inputStream: InputStream) : List<Array<String>> {
        return try {
            CSVReader(inputStream.reader()).readAll()
        } catch (e: IOException) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace()
            }
            listOf()
        }
    }
}