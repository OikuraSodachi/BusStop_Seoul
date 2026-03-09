package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.busstop_seoul.dataclass.searchresult.LineSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.StationSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult
import com.todokanai.domain.BusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val busUseCase: BusUseCase
): ViewModel(){

    private val keyWord = MutableStateFlow<String>("")

    val uiState = keyWord.map{ word->
        MainScreenUiState(
            results = getSearchData(word),
            favorites = emptyList()
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

    suspend fun getSearchData(keyWord:String):List<SearchResult>{
        val result = mutableListOf<SearchResult>()
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
                        it.stationTp
                    )
                )
            }
            // Todo: 다른 type 의 SearchResult 도 추가. keyWord 에서 routeId 추출과정에는 room 활용하기

            val lineList = busUseCase.getLineInfosFromKeyWord(keyWord)
            lineList.forEach {
                result.add(
                    LineSearchResult(
                        it.busRouteId,
                        it.rtNm
                    )
                )
            }
        }

        return result
    }

}

data class MainScreenUiState(
    val results:List<SearchResult> = emptyList(),
    val favorites:List<SearchResult> = emptyList()
)