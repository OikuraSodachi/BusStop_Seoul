package com.todokanai.domain

import com.todokanai.domain.linearrivetest.LineArriveTest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LineArriveServiceTest {

    @GET("")
    fun getStationArrive(@Query("nodeId") nodeId: String ): Call<LineArriveTest>

}