package com.todokanai.busstop_seoul.dataclass.abstracts

/** [com.todokanai.busstop_seoul.compose.holder.SearchResultHolder] 에 사용할 abstract class **/
abstract class SearchResult {

    abstract fun onItemClick()

    /** Todo: enum class 사용하기? **/
    abstract fun type()

}