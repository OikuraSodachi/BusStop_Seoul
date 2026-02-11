package com.todokanai.busstop_seoul.dataclass

import com.todokanai.busstop_seoul.dataclass.abstracts.SearchResult

data class LineSearchResult(
    val lineId:Long,
    val lineName:String
): SearchResult() {
    override fun onItemClick() {
        TODO("Not yet implemented")
    }

    override fun type() {
    }
}

