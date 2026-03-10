package com.todokanai.busstop_seoul.dataclass.searchresult.abstracts

import androidx.navigation.NavHostController

/** [com.todokanai.busstop_seoul.compose.holder.SearchResultHolder] 에 사용할 abstract class **/
abstract class SearchResult {

    abstract fun description():String

    abstract fun onItemClick(navController: NavHostController)

    abstract fun type():ResultType

    abstract fun addToFavorite()

}

enum class ResultType(val code:Int){
    LINE(0),
    STATION(1)
}