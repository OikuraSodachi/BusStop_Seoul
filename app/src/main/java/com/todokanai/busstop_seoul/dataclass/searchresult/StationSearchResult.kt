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
    val stationTp:Int?
): SearchResult(){
    override fun description(): String {
        return stNm
    }

    override fun onItemClick(navController: NavHostController) {
        navController.navigateToMapScreen(arsId,stNm)
    }

    override fun type(): ResultType {
        return ResultType.STATION
    }

    override fun addToFavorite() {

    }

}
