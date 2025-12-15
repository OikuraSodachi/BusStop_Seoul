package com.todokanai.domain

import com.todokanai.domain.response.StationArriveItem

interface StationRepository {

  suspend fun getStationArriveInfos(key:Long): List<StationArriveItem>

}