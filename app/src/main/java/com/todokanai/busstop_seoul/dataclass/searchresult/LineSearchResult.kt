package com.todokanai.busstop_seoul.dataclass.searchresult

import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

data class LineSearchResult(
    val lineId:Long,
    val lineName:String
): SearchResult(
    description = lineName,
    type = ResultType.LINE
)