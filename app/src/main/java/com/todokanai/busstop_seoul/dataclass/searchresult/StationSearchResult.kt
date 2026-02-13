package com.todokanai.busstop_seoul.dataclass.searchresult

import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult

data class StationSearchResult(
    val stationName: String
): SearchResult(){
    override fun description(): String {
        return stationName
    }

    override fun onItemClick() {
        TODO("Not yet implemented")
    }

    override fun type(): ResultType {
        return ResultType.STATION
    }

}
