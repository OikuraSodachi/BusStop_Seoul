package com.todokanai.domain

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder


object ApiExplorer {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val urlBuilder =
            StringBuilder("http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid") /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=서비스키") /*Service Key*/
        urlBuilder.append(
            "&" + URLEncoder.encode(
                "arsId",
                "UTF-8"
            ) + "=" + URLEncoder.encode("12121", "UTF-8")
        ) /*정류소 번호*/
        val url = URL(urlBuilder.toString())
        val conn = url.openConnection() as HttpURLConnection
        conn.setRequestMethod("GET")
        conn.setRequestProperty("Content-type", "application/json")
        println("Response code: " + conn.getResponseCode())
        val rd: BufferedReader?
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = BufferedReader(InputStreamReader(conn.getInputStream()))
        } else {
            rd = BufferedReader(InputStreamReader(conn.getErrorStream()))
        }
        val sb = StringBuilder()
        var line: String?
        while ((rd.readLine().also { line = it }) != null) {
            sb.append(line)
        }
        rd.close()
        conn.disconnect()
        println(sb.toString())
    }
}
class MyClass {
}