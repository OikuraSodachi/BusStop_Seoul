package com.todokanai.busstop_seoul.dataclass.searchresult

import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.compose.navigation.navigateToLineInfo
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

data class LineSearchResult(
    val lineId:Long,
    val lineName:String
): SearchResult(
    description = lineName,
    type = ResultType.LINE
){
    override fun onItemClick(navController: NavHostController) {
        navController.navigateToLineInfo(routeId = lineId)
    }
}