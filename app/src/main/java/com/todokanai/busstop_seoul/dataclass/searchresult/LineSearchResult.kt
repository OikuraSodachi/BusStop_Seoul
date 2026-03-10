package com.todokanai.busstop_seoul.dataclass.searchresult

import androidx.navigation.NavHostController
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

data class LineSearchResult(
    val lineId:Long,
    val lineName:String
): SearchResult() {

    override fun description(): String {
        return lineName
    }
    override fun onItemClick(navController: NavHostController) {

    }

    override fun type() : ResultType {
        return ResultType.LINE
    }

    override fun addToFavorite() {

    }
}