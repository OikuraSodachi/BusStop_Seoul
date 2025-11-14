package com.todokanai.data.repository

import com.todokanai.data.dataclass.BusArrive
import com.todokanai.domain.BusArriveRetrofit
import com.todokanai.domain.StationRepository

class StationRepositoryImpl : StationRepository {
    override fun getBusArrive(nodId: String) {
        val result = mutableListOf<BusArrive>()
        BusArriveRetrofit.retrofit.create()
    }

}