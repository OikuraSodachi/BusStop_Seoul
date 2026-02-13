package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.busstop_seoul.dataclass.searchresult.StationSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult
import com.todokanai.domain.BusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val busUseCase: BusUseCase
): ViewModel(){

    private val keyWord = MutableStateFlow<String>("")

    val uiState = keyWord.map{ word->
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
            this@SearchScreenViewModel.keyWord.value = keyWord      // Todo: value 대신 emit / update 사용 고려하기
        }
    }

    suspend fun getSearchData(keyWord:String):List<SearchResult>{
        val stationList = busUseCase.getStationByName(keyWord).map {
            StationSearchResult(
                it.stNm.toString()
            )
        }
        return stationList
    }

}

data class SearchScreenUiState(
    val results:List<SearchResult> = emptyList()
)