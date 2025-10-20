package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo
import com.todokanai.domain.MapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mapUseCase: MapUseCase
): ViewModel()  {

    /** StationInfoScreen 에 필요 **/
    private val _arriveInfoFlow = MutableStateFlow<List<StationArriveInfo>>(emptyList())
    val arriveInfoFlow = _arriveInfoFlow.asStateFlow()

    val uiState = combine(
        arriveInfoFlow,
        mapUseCase.smallMapEnabled(),
        mapUseCase.zoomControlsEnabled(),
        mapUseCase.rotationGesturesEnabled()
    ){ arriveInfos,smallMapEnabled, zoomControlsEnabled, rotationGesturesEnabled ->
        MainActivityUiState(
            isSmallMapEnabled = smallMapEnabled,
            mapUiSettings = MapUiSettings(
                zoomControlsEnabled = zoomControlsEnabled,
                rotationGesturesEnabled = rotationGesturesEnabled
            ),
            markerInfos = listOf(
                MarkerInfo(
                    id = 0,
                    position = LatLng(1.35, 103.87),
                    title = "Singapore",
                    snippet = "Marker in Singapore"
                )
            ),
            arriveInfos = arriveInfos
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainActivityUiState()
    )

    fun saveSmallMapEnabled(value: Boolean){
        viewModelScope.launch {
            mapUseCase.saveSmallMapEnabled(value)
        }
    }

    fun saveRotationGesturesEnabled(value: Boolean) {
        viewModelScope.launch {
            mapUseCase.saveRotationGesturesEnabled(value)
        }
    }

}

data class MainActivityUiState(
    val isSmallMapEnabled: Boolean = false,
    val mapUiSettings: MapUiSettings = MapUiSettings(),
    val markerInfos:List<MarkerInfo> = emptyList(),
    val arriveInfos:List<StationArriveInfo> = emptyList()
)