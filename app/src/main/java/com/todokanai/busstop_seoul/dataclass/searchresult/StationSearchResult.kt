package com.todokanai.busstop_seoul.dataclass.searchresult

import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.compose.navigation.navigateToMapScreen
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult
import com.todokanai.domain.dataclass.StationItem

data class StationSearchResult(
    val stId:Long,
    val stNm: String,
    val arsId:Long,
    val tmX:Double,
    val tmY:Double,
    val posX:String?,
    val posY:String?,
    val stationTp:String?,
    val favorite:Boolean
): SearchResult(
    description = stNm,
    isFavorite = favorite,
    type = ResultType.STATION,
    optionalInfo = arsId.toString()
){
    override fun onItemClick(navController: NavHostController) {
        navController.navigateToMapScreen(stId = stId)
    }

    fun toStationItem(): StationItem{
        return StationItem(
            stId = stId,
            stNm = stNm,
            arsId = arsId,
            tmX = tmX,
            tmY = tmY,
            posX = posX,
            posY = posY,
            stationTp = stationTp
        )
    }
}