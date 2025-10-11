package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.domain.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
): ViewModel()  {

    val uiState = combine(
        settingsRepository.smallMapEnabled(),
        settingsRepository.zoomControlsEnabled(),
    ){ smallMapEnabled, zoomControlsEnabled ->
        MainActivityUiState(
            isSmallMapEnabled = smallMapEnabled,
            mapUiSettings = MapUiSettings(
                zoomControlsEnabled = zoomControlsEnabled,
            ),
            markerInfos = listOf(
                MarkerInfo(
                    id = 0,
                    position = LatLng(1.35, 103.87),
                    title = "Singapore",
                    snippet = "Marker in Singapore"
                )
            )
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainActivityUiState()
    )

    fun saveSmallMapEnabled(value: Boolean){
        viewModelScope.launch {
            settingsRepository.saveSmallMapEnabled(value)
        }
    }

}

data class MainActivityUiState(
    val isSmallMapEnabled: Boolean = false,
    val mapUiSettings: MapUiSettings = MapUiSettings(),
    val markerInfos:List<MarkerInfo> = emptyList()
)