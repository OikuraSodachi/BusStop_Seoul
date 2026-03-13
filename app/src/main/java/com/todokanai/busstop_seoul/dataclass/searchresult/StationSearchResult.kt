package com.todokanai.busstop_seoul.dataclass.searchresult

import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.compose.navigation.navigateToMapScreen
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

data class StationSearchResult(
    val stId:Long,
    val stNm: String,
    val arsId:Long,
    val tmX:Double,
    val tmY:Double,
    val posX:String?,
    val posY:String?,
    val stationTp:Int?,
    val favorite:Boolean
): SearchResult(
    description = stNm,
    isFavorite = favorite,
    type = ResultType.STATION
){
    override fun onItemClick(navController: NavHostController) {
        navController.navigateToMapScreen(arsId = arsId,stNm = stNm)
    }
}