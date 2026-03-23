package com.todokanai.busstop_seoul.viewmodel

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.busstop_seoul.dataclass.searchresult.LineSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.StationSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult
import com.todokanai.busstop_seoul.di.MyApplication.Companion.appContext
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

    init {
        test(appContext.assets)
    }
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

    }

    fun deleteSearchData(data:SearchResult){

    }

    suspend fun getSearchData(keyWord:String):List<SearchResult>{
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
            // Todo: 다른 type 의 SearchResult 도 추가. keyWord 에서 routeId 추출과정에는 room 활용하기

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


    fun test(assetManager: AssetManager){
        viewModelScope.launch {
            val csv = assetManager.open("SeoulBusStation.csv")
            val test = busUseCase.test(csv)
        }
    }
}

data class MainScreenUiState(
    val results:List<SearchResult> = emptyList(),
    val favorites:List<SearchResult> = emptyList()
)