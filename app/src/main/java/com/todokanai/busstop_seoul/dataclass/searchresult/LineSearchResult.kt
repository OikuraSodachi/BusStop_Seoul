package com.todokanai.busstop_seoul.dataclass.searchresult

import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.compose.navigation.navigateToLineInfo
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

data class LineSearchResult(
    val busRouteId:Long,
    val rtNm:String,
    val routeAbrv: String? = null,
    val routeType: String? = null,
    val stBegin: String? = null,
    val stEnd: String? = null,
    val term: String? = null,
    val firstBusTm: String? = null,
    val lastBusTm: String? = null,
    val favorite:Boolean
): SearchResult(
    description = rtNm,
    isFavorite = favorite,
    type = ResultType.LINE
){
    override fun onItemClick(navController: NavHostController) {
        navController.navigateToLineInfo(routeId = busRouteId)
    }
}