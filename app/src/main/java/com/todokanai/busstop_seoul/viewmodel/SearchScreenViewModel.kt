package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.busstop_seoul.dataclass.searchresult.LineSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.StationSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult
import com.todokanai.domain.BusUseCase
import com.todokanai.domain.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val busUseCase: BusUseCase
) : ViewModel() {

    private val keyWord = MutableStateFlow<String>("")

    val uiState = combine(
        keyWord,
        MutableStateFlow<String>("")       //  Todo: dummy Flow. 나중에 지울 것
    ) { word, _ ->
        SearchScreenUiState(
            results = getSearchData(word)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SearchScreenUiState()
    )

    fun onKeyWordChanged(keyWord:String){
        viewModelScope.launch {
            this@SearchScreenViewModel.keyWord.value = keyWord
        }
    }

    fun saveToFavorites(searchResult: SearchResult){
        viewModelScope.launch {
            when(searchResult.type){
                ResultType.STATION -> {
                    val item = searchResult as StationSearchResult
                    busUseCase.saveBusStation(item.toStationItem())
                }
                ResultType.LINE -> {
                    val item = searchResult as LineSearchResult
                    busUseCase.saveBusLine(item.toBusLineItem())
                }
            }
        }

    }

    fun deleteFromFavorites(searchResult: SearchResult){
        viewModelScope.launch {
            when(searchResult.type){
                ResultType.STATION -> {
                    val item = searchResult as StationSearchResult
                    busUseCase.deleteBusStation(item.stId)
                }
                ResultType.LINE -> {
                    val item = searchResult as LineSearchResult
                    busUseCase.deleteBusLine(item.busRouteId)
                }
            }
        }
    }

    private suspend fun getSearchData(keyWord:String):List<SearchResult>{
        val result = mutableListOf<SearchResult>()

        val favoriteStations = emptyList<Long>()
        val favoriteLines = emptyList<Long>()

        if(keyWord.isNotBlank()) {
            val stationList = searchUseCase.getStationByName(keyWord)
            stationList.forEach {
                result.add(
                    StationSearchResult(
                        it.stId,
                        it.stNm,
                        it.arsId,
                        it.tmX,
                        it.tmY,
                        it.posX,
                        it.posY,
                        it.stationTp,
                        favoriteStations.contains(it.stId)
                    )
                )
            }

            val lineList = searchUseCase.getLineInfosFromKeyWord(keyWord)
            lineList.forEach {
                result.add(
                    LineSearchResult(
                        it.busRouteId,
                        it.rtNm,
                        it.routeAbrv,
                        it.routeType,
                        it.stBegin,
                        it.stEnd,
                        it.term,
                        it.firstBusTm,
                        it.lastBusTm,
                        favoriteLines.contains(it.busRouteId)
                    )
                )
            }
        }

        return result
    }

}

data class SearchScreenUiState(
    val results: List<SearchResult> = emptyList()
)