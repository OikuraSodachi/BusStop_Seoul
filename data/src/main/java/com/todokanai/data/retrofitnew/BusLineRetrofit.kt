package com.todokanai.data.retrofitnew

import com.todokanai.data.Constants.BUS_LINE_BASE_URL
import retrofit2.Retrofit

object BusLineRetrofit {

    val busLineRetrofit = Retrofit.Builder()
        .baseUrl(BUS_LINE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}