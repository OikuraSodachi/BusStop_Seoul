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
import com.todokanai.domain.BusUseCase
import com.todokanai.domain.MapUseCase
import com.todokanai.domain.dataclass.StationArriveItem
import com.todokanai.domain.dataclass.StationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapUseCase: MapUseCase,
    private val busUseCase: BusUseCase
): ViewModel()  {

    private val markerInfos = MutableStateFlow<List<MarkerInfo>>(emptyList())

    val uiState = combine(
        mapUseCase.smallMapEnabled(),
        mapUseCase.zoomControlsEnabled(),
        mapUseCase.rotationGesturesEnabled(),
        markerInfos
    ){smallMapEnabled, zoomControlsEnabled, rotationGesturesEnabled, markers->
        MapScreenUiState(
            isSmallMapEnabled = smallMapEnabled,
            mapUiSettings = MapUiSettings(
                zoomControlsEnabled = zoomControlsEnabled,
                mapToolbarEnabled = false,
                rotationGesturesEnabled = rotationGesturesEnabled
            ),
            markerInfos = markers
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MapScreenUiState()
    )

    // Todo: mainMapCallback 을 함수가 아닌 변수 (val) 로서 가지고 있는 것이 메모리 관리상 적절한지 고민해볼 것
    val mainMapCallback = object: MainMapInterface {
        override fun onVisibleRegionChanged(latLngBounds: LatLngBounds) {
            viewModelScope.launch {
                val radiusInMeters = getRadiusInMeters(latLngBounds)
                val isMarkerActive = radiusInMeters<MAP_MARKER_MINIMUM_RADIUS        // marker 기능 활성화 여부 결정

                val result = if(isMarkerActive) {
                    getVisibleStation(
                        tmX = latLngBounds.center.longitude,
                        tmY = latLngBounds.center.latitude,
                        radius = radiusInMeters
                    ).map {
                        MarkerInfo(
                            stationInfo = it,
                            position = LatLng(it.tmY, it.tmX),
                            title = it.stNm,
                            snippet = null
                        )

                    }
                }else{
                    emptyList()
                }
                markerInfos.update{result}
            }
        }

    }

    suspend fun getArriveInfos(key:Long) : List<StationArriveInfo>{
        return busUseCase.getArriveInfos(key).map{
            it.toStationArriveInfo()
        }
    }

    /** Todo: arsId 값만으로 StationInfo 를 가져올 방법은 없는지? **/
    suspend fun getStationInfo(arsId:Long, stNm:String): StationInfo?{
        val temp = busUseCase.getStationByName(stNm).filter { it.arsId == arsId }
        return temp[0].toStationInfo()
    }

    private suspend fun getVisibleStation(
        tmX: Double,
        tmY: Double,
        radius: Int
    ): List<StationInfo> {
        val result = busUseCase.getStationByPosition(
            tmX = tmX,
            tmY = tmY,
            radius = radius
        ).map{
            it.toStationInfo()
        }
        return result
    }

    fun saveSmallMapEnabled(value: Boolean) {
        viewModelScope.launch {
            mapUseCase.saveSmallMapEnabled(value)
        }
    }

    fun saveRotationGesturesEnabled(value: Boolean) {
        viewModelScope.launch {
            mapUseCase.saveRotationGesturesEnabled(value)
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

    private fun StationArriveItem.toStationArriveInfo(): StationArriveInfo {
        return StationArriveInfo(
            stId = stId,
            stNm = stNm,
            arsId = arsId,
            busRouteId = busRouteId,
            rtNm = rtNm,
            busRouteAbrv = busRouteAbrv,
            sectNm = sectNm,
            gpsX = gpsX,
            gpsY = gpsY,
            stationTp = stationTp,
            firstTm = firstTm,
            lastTm = lastTm,
            term = term,
            routeType = routeType,
            nextBus = nextBus,
            staOrd = staOrd,
            vehId1 = vehId1,
            sectOrd1 = sectOrd1,
            stationNm1 = stationNm1,
            traTime1 = traTime1,
            traSpd1 = traSpd1,
            isArrive1 = isArrive1,
            repTm1 = repTm1,
            isLast1 = isLast1,
            busType1 = busType1,
            vehId2 = vehId2,
            sectOrd2 = sectOrd2,
            stationNm2 = stationNm2,
            traTime2 = traTime2,
            traSpd2 = traSpd2,
            isArrive2 = isArrive2,
            isLast2 = isLast2,
            busType2 = busType2,
            adirection = adirection,
            arrmsg1 = arrmsg1,
            arrmsg2 = arrmsg2,
            arrmsgSec1 = arrmsgSec1,
            arrmsgSec2 = arrmsgSec2,
            nxtStn = nxtStn,
            rerdieDiv1 = rerdieDiv1,
            rerdieDiv2 = rerdieDiv2,
            rerideNum1 = rerideNum1,
            rerideNum2 = rerideNum2,
            isFullFlag1 = isFullFlag1,
            isFullFlag2 = isFullFlag2,
            deTourAt = deTourAt,
            congestion1 = congestion1,
            congestion2 = congestion2,
            remndrNmpr1 = remndrNmpr1,
            remndrNmpr2 = remndrNmpr2
        )
    }

    private fun StationItem.toStationInfo(): StationInfo {
        return StationInfo(
            stId = stId,
            stNm = stNm,
            arsId = arsId,
            tmX = tmX,
            tmY = tmY,
            posX = posX,
            posY = posY,
            stationTp = stationTp
        )
    }

}

data class MapScreenUiState(
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