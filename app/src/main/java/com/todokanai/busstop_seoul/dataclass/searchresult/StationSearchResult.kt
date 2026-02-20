package com.todokanai.busstop_seoul.dataclass.searchresult

import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.compose.navigation.navigateToMainScreen
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

data class StationSearchResult(
    val stationName: String,
    val arsId:Long
): SearchResult(){
    override fun description(): String {
        return stationName
    }

    override fun onItemClick(navController: NavHostController) {
        navController.navigateToMainScreen(arsId)
    }

    override fun type(): ResultType {
        return ResultType.STATION
    }

}
