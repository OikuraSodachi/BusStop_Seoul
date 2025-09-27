package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(): ViewModel() {

    val mapUiState : StateFlow<MapUiState> = MutableStateFlow(MapUiState())

}

data class MapUiState(
    val dummyData:String = ""
)