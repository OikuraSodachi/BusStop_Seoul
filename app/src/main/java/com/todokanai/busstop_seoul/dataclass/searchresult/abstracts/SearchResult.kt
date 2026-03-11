package com.todokanai.busstop_seoul.dataclass.searchresult.abstracts


/** [com.todokanai.busstop_seoul.compose.holder.SearchResultHolder] 에 사용할 abstract class **/
abstract class SearchResult(
    val description:String,
    val type:ResultType
)

enum class ResultType(val code:Int){
    LINE(0),
    STATION(1)
}