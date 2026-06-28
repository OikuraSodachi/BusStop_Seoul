package com.todokanai.busstop_seoul.dataclass.searchresult.abstracts

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController

/** [com.todokanai.busstop_seoul.compose.holder.SearchResultHolder] 에 사용할 abstract class **/
@Stable
abstract class SearchResult(
    val description:String,
    val isFavorite:Boolean,
    val type:ResultType,
    val optionalInfo: String? = null
){
    abstract val id: Long

    /** Todo: navController 가 여기서 보이는 게 적절한지? **/
    abstract fun onItemClick(navController: NavHostController)
}

enum class ResultType(val code:Int){
    LINE(0),
    STATION(1)
}