package com.todokanai.busstop_seoul.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.MapUiSettings
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo
import com.todokanai.busstop_seoul.interfaces.MainMapInterface
import com.todokanai.busstop_seoul.util.MainMapCallback
import com.todokanai.domain.MapUseCase
import com.todokanai.domain.StationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mapUseCase: MapUseCase,
    private val stationUseCase: StationUseCase
): ViewModel()  {

    val uiState = combine(
        mapUseCase.smallMapEnabled(),
        mapUseCase.zoomControlsEnabled(),
        mapUseCase.rotationGesturesEnabled()
    ){smallMapEnabled, zoomControlsEnabled, rotationGesturesEnabled ->
        MainActivityUiState(
            isSmallMapEnabled = smallMapEnabled,
            mapUiSettings = MapUiSettings(
                zoomControlsEnabled = zoomControlsEnabled,
                mapToolbarEnabled = false,
                rotationGesturesEnabled = rotationGesturesEnabled
            ),
            markerInfos = listOf(
                MarkerInfo(
                    id = 0,
                    position = LatLng(37.532600, 127.024612),
                    title = "Seoul",
                    snippet = "Marker in Seoul"
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
            mapUseCase.saveSmallMapEnabled(value)
        }
    }

    fun saveRotationGesturesEnabled(value: Boolean) {
        viewModelScope.launch {
            mapUseCase.saveRotationGesturesEnabled(value)
        }
    }

    suspend fun getArriveInfos_dummy(key:Long):List<StationArriveInfo>{
        val testKey = 11111L
        val testString ="경성"

        val stationNames = stationUseCase.getStationByName(testString)
        println(stationNames)

        return stationUseCase.getArriveInfos(testKey).map{
            StationArriveInfo(
                lineNumber = it.rtNm.toString(),
                estTime = it.arrmsg1.toString()
            )
        }
    }

    // Todo: mainMapCallback 을 함수가 아닌 변수 (val) 로서 가지고 있는 것이 메모리 관리상 적절한지 고민해볼 것
    val mainMapCallback : MainMapInterface = MainMapCallback()


}

data class MainActivityUiState(
    val isSmallMapEnabled: Boolean = false,
    val mapUiSettings: MapUiSettings = MapUiSettings(),
    val markerInfos:List<MarkerInfo> = emptyList(),
    val smallMapSettings: MapUiSettings =  MapUiSettings(
        zoomControlsEnabled = false,
        mapToolbarEnabled = false,
        compassEnabled = false,
        myLocationButtonEnabled = false,
        indoorLevelPickerEnabled = false,
        rotationGesturesEnabled = false,
        scrollGesturesEnabled = false,
        tiltGesturesEnabled = false,
        zoomGesturesEnabled = false
    )
)