package com.todokanai.domain

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LineArriveRetrofit {
    val BASE_URL = "https://ws.bus.go.kr/api/rest/arrive/getArrInfoByRouteAll/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}