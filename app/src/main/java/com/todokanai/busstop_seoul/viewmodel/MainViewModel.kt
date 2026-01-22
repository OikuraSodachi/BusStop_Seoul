package com.todokanai.busstop_seoul.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.MapUiSettings
import com.todokanai.busstop_seoul.Constants.MAP_MARKER_MINIMUM_RADIUS
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo
import com.todokanai.busstop_seoul.dataclass.StationInfo
import com.todokanai.busstop_seoul.interfaces.compose.MainMapInterface
import com.todokanai.busstop_seoul.interfaces.compose.MainScreenInterface
import com.todokanai.domain.MapUseCase
import com.todokanai.domain.StationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val targetStation = MutableStateFlow<StationInfo?>(null)
    private val markerInfos = MutableStateFlow<List<MarkerInfo>>(emptyList())

    val uiState = combine(
        mapUseCase.smallMapEnabled(),
        mapUseCase.zoomControlsEnabled(),
        mapUseCase.rotationGesturesEnabled(),
        markerInfos,
        targetStation
    ){smallMapEnabled, zoomControlsEnabled, rotationGesturesEnabled, markers, targetStation ->
        println("test: ${markers}")
        MainActivityUiState(
            isSmallMapEnabled = smallMapEnabled,
            mapUiSettings = MapUiSettings(
                zoomControlsEnabled = zoomControlsEnabled,
                mapToolbarEnabled = false,
                rotationGesturesEnabled = rotationGesturesEnabled
            ),
            markerInfos = markers,
            targetStation = targetStation
        )

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MainActivityUiState()
    )

    // Todo: mainMapCallback 을 함수가 아닌 변수 (val) 로서 가지고 있는 것이 메모리 관리상 적절한지 고민해볼 것
    val mainMapCallback = object: MainMapInterface {
        override fun onMarkerClick(markerInfo: MarkerInfo) {
            viewModelScope.launch {
                println("onMarkerClick: ${markerInfo}")
                val result = markerInfos.value.first{it == markerInfo}
                targetStation.value = result.stationInfo
            }
        }

        override fun onVisibleRegionChanged(latLngBounds: LatLngBounds) {
            viewModelScope.launch {
                val radiusInMeters = getRadiusInMeters(latLngBounds)

                val isMarkerActive = radiusInMeters<MAP_MARKER_MINIMUM_RADIUS        // marker 기능 활성화 여부 결정

                if(isMarkerActive) {
                    val result= mainScreenCallback.getVisibleStation(
                        tmX = latLngBounds.center.longitude,
                        tmY = latLngBounds.center.latitude,
                        radius = radiusInMeters
                    )
                    markerInfos.value = result.map{
                        MarkerInfo(
                            stationInfo = it,
                            position = LatLng(it.tmY.toDouble(), it.tmX.toDouble()),
                            title = it.stNm,
                            snippet = null
                        )
                    }
                }else{
                    markerInfos.value = emptyList()
                }
            }
        }

    }

    val mainScreenCallback = object : MainScreenInterface {
        override suspend fun getArriveInfos(key: Long): List<StationArriveInfo> {
            return stationUseCase.getArriveInfos(key).map{
                StationArriveInfo(
                    lineNumber = it.rtNm.toString(),
                    estTime = it.arrmsg1.toString()
                )
            }
        }
        override suspend fun getVisibleStation(
            tmX: Double,
            tmY: Double,
            radius: Int
        ): List<StationInfo> {
            val result = stationUseCase.getStationByPosition(
                tmX = tmX,
                tmY = tmY,
                radius = radius
            ).map{
                StationInfo(
                    stId = it.stId.toString(),
                    stNm = it.stNm.toString(),
                    arsId = it.arsId.toString(),
                    tmX = it.tmX.toString(),
                    tmY = it.tmY.toString(),
                    posX = it.posX.toString(),
                    posY = it.posY.toString()
                )
            }
            return result
        }
        override fun saveSmallMapEnabled(value: Boolean) {
            viewModelScope.launch {
                mapUseCase.saveSmallMapEnabled(value)
            }
        }
        override fun saveRotationGesturesEnabled(value: Boolean) {
            viewModelScope.launch {
                mapUseCase.saveRotationGesturesEnabled(value)
            }
        }

        override fun invalidateTargetStation() {
            viewModelScope.launch {
                targetStation.value = null
            }
        }
    }

    private fun getRadiusInMeters(latLngBounds: LatLngBounds): Int {
        val results = FloatArray(1)
        Location.distanceBetween(
            latLngBounds.northeast.latitude, latLngBounds.northeast.longitude,
            latLngBounds.southwest.latitude, latLngBounds.southwest.longitude,
            results
        )
        val radiusInMeters = (results[0] / 2).toInt()
        return radiusInMeters
    }

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
    ),
    val targetStation: StationInfo? = null
)