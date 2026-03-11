package com.todokanai.busstop_seoul.dataclass.searchresult

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
): SearchResult(
    description = stNm,
    type = ResultType.STATION
)