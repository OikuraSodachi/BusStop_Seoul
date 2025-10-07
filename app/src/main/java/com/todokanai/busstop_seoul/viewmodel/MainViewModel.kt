package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import com.google.maps.android.compose.MapUiSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel()  {
    private val _uiState = MutableStateFlow(MainActivityUiState())
    val uiState = _uiState.asStateFlow()
}

data class MainActivityUiState(
    val dummy:Int = 0,
    val mapUiSettings: MapUiSettings = MapUiSettings()
)