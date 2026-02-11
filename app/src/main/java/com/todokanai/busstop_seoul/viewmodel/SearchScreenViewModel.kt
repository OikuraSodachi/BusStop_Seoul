package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todokanai.busstop_seoul.interfaces.compose.SearchDataInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(): ViewModel(){

    private val keyWord = MutableStateFlow<String>("")

    val uiState = combine(
        keyWord
    ){ searchData ->
        SearchScreenUiState(
            dummyData = emptyList()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = SearchScreenUiState()
    )
    val searchDataCallback = object : SearchDataInterface {
        override fun onItemClick() {
            TODO("Not yet implemented")
        }

    }

}

data class SearchScreenUiState(
    val dummyData:List<String> = emptyList()
)