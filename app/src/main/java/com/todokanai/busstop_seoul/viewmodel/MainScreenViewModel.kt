package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.busstop_seoul.dataclass.searchresult.LineSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.StationSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult
import com.todokanai.domain.BusUseCase
import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val busUseCase: BusUseCase
): ViewModel(){

    private val keyWord = MutableStateFlow<String>("")

    val uiState = combine(
        keyWord,
        busUseCase.getSavedStationItems(),
        busUseCase.getSavedBusLineItems()
    ) { word , stations,lines->
        MainScreenUiState(
            results = getSearchData(word),
            favorites = getFavorites(stations, lines)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainScreenUiState()
    )

    fun onKeyWordChanged(keyWord:String){
        viewModelScope.launch {
            this@MainScreenViewModel.keyWord.update{keyWord}
        }
    }

    fun saveToFavorite(data:SearchResult){
        viewModelScope.launch {
            when(data.type){
                ResultType.STATION -> {
                    val item = data as StationSearchResult
                    busUseCase.saveBusStation(
                        StationItem(
                            item.stId,
                            item.stNm,
                            item.arsId,
                            item.tmX,
                            item.tmY,
                            item.posX,
                            item.posY,
                            item.stationTp
                        )
                    )
                }
                ResultType.LINE -> {
                    val item = data as LineSearchResult
                    busUseCase.saveBusLine(
                        BusLineItem(
                            item.busRouteId,
                            item.rtNm,
                            item.routeAbrv,
                            item.routeType,
                            item.stBegin,
                            item.stEnd,
                            item.term,
                            item.firstBusTm,
                            item.lastBusTm
                        )
                    )
                }

            }
        }
    }

    fun deleteSearchData(data:SearchResult){
        viewModelScope.launch {
            when(data.type){
                ResultType.STATION -> {
                    val item = data as StationSearchResult
                    busUseCase.deleteBusStation(item.stId)
                }
                ResultType.LINE -> {
                    val item = data as LineSearchResult
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
            val stationList = busUseCase.getStationByName(keyWord)
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

            val lineList = busUseCase.getLineInfosFromKeyWord(keyWord)
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

    private fun getFavorites(
        stations:List<StationItem>,
        lines:List<BusLineItem>
    ):List<SearchResult>{
        val result = mutableListOf<SearchResult>()

        stations.forEach {
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
                    true
                )
            )
        }

        lines.forEach {
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
                    true
                )
            )
        }
        return result
    }

}

data class MainScreenUiState(
    val results:List<SearchResult> = emptyList(),
    val favorites:List<SearchResult> = emptyList()
)