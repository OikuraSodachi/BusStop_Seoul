package com.todokanai.busstop_seoul.dataclass.searchresult

import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

data class LineSearchResult(
    val lineId:Long,
    val lineName:String
): SearchResult() {

    override fun description(): String {
        return lineName
    }
    override fun onItemClick() {
        TODO("Not yet implemented")
    }

    override fun type() {
    }
}