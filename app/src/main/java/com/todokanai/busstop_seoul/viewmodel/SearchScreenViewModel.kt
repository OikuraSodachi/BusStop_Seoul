package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import com.todokanai.busstop_seoul.dataclass.searchresult.abstracts.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor() : ViewModel() {

    val uiState = MutableStateFlow<SearchScreenUiState>(
        SearchScreenUiState()
    )

    fun onKeyWordChanged(keyWord:String){

    }

    fun saveToFavorites(searchResult: SearchResult){

    }

    fun deleteFromFavorites(searchResult: SearchResult){

    }

}

data class SearchScreenUiState(
    val results: List<SearchResult> = emptyList()
)