package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.busstop_seoul.dataclass.searchresult.LineSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.StationSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.ResultType
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult
import com.todokanai.busstop_seoul.util.toLineSearchResult
import com.todokanai.busstop_seoul.util.toStationSearchResult
import com.todokanai.domain.BusUseCase
import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val busUseCase: BusUseCase
): ViewModel(){

    val uiState = combine(
        busUseCase.getSavedStationItems(),
        busUseCase.getSavedBusLineItems()
    ) { stations,lines->
        MainScreenUiState(
            history = emptyList(),
            favorites = getFavorites(stations, lines)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainScreenUiState()
    )

    fun saveToFavorite(data:SearchResult){
        viewModelScope.launch {
            when(data.type){
                ResultType.STATION -> {
                    val item = data as StationSearchResult
                    busUseCase.saveBusStation(item.toStationItem())
                }
                ResultType.LINE -> {
                    val item = data as LineSearchResult
                    busUseCase.saveBusLine(item.toBusLineItem())
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

    private fun getFavorites(
        stations:List<StationItem>,
        lines:List<BusLineItem>
    ):List<SearchResult>{
        val result = mutableListOf<SearchResult>()

        stations.forEach {
            result.add(
                it.toStationSearchResult(true)
            )
        }

        lines.forEach {
            result.add(
                it.toLineSearchResult(true)
            )
        }
        return result
    }

}

data class MainScreenUiState(
    val history: List<SearchResult> = emptyList(),
    val favorites:List<SearchResult> = emptyList()
)