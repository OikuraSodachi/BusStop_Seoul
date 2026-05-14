package com.todokanai.busstop_seoul.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng             //  Todo: viewModel 에서 제거
import com.google.android.gms.maps.model.LatLngBounds       //  Todo: viewModel 에서 제거
import com.todokanai.busstop_seoul.Constants.MAP_MARKER_MINIMUM_RADIUS
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.dataclass.RangeSearchItem
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo
import com.todokanai.busstop_seoul.dataclass.StationInfo
import com.todokanai.domain.BusUseCase
import com.todokanai.domain.MapUseCase
import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationArriveItem
import com.todokanai.domain.dataclass.StationItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
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
            zoomControlsEnabled = zoomControlsEnabled,
            mapToolbarEnabled = false,
            rotationGesturesEnabled = rotationGesturesEnabled,
            markerInfos = markers
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MapScreenUiState()
    )

    suspend fun lastKnownLatLng():LatLng{
        return LatLng(
            mapUseCase.lastKnownLatitude().first(),
            mapUseCase.lastKnownLongitude().first()
        )
    }

    suspend fun lastKnownZoomLevel():Float{
        return mapUseCase.lastKnownZoomLevel().first()
    }

    // Todo: Compose dependency 가 viewModel 에 와도 되는지?
    fun testCameraPositionChanged(latLngBounds: LatLngBounds,zoomLevel:Float) {
        viewModelScope.launch {
            val radiusInMeters = getRadiusInMeters(latLngBounds)
            val isMarkerActive =
                radiusInMeters < MAP_MARKER_MINIMUM_RADIUS        // marker 기능 활성화 여부 결정

            val latitude = latLngBounds.center.latitude
            val longitude = latLngBounds.center.longitude

            val result = if (isMarkerActive) {
                getVisibleStation(
                    tmX = longitude,
                    tmY = latitude,
                    radius = radiusInMeters
                ).map {
                    MarkerInfo(
                        stationInfo = it,
                        position = LatLng(it.tmY, it.tmX),
                        title = it.stNm,
                        snippet = null
                    )
                }
            } else {
                emptyList()
            }
            markerInfos.update { result }

            mapUseCase.saveLastKnownLatitude(latitude)
            mapUseCase.saveLastKnownLongitude(longitude)

            mapUseCase.saveLastKnownZoomLevel(zoomLevel)
        }
    }

    suspend fun getArriveInfos(key:Long) : List<StationArriveInfo>{
        return busUseCase.getArriveInfos(key).map{
            it.toStationArriveInfo()
        }
    }

    suspend fun getStationInfo(stId:Long?):StationInfo?{
        val result =
            if(stId != null){
                busUseCase.getStationById(stId)?.toStationInfo()
            }else{
                null
            }
        return result
    }

    /** @param startArsIds 시작 정류소 ID 목록
     * @param endArsIds 도착 정류소 ID 목록
     * @return 검색 결과 **/
    suspend fun rangeSearchResult(startArsIds:List<Long>, endArsIds:List<Long>):List<RangeSearchItem>{
        return busUseCase.getRangeSearchResult(startArsIds, endArsIds).map {
            it.toRangeSearchItem()
        }
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
            busRouteId = busRouteId,
            rtNm = rtNm,
            busRouteAbrv = busRouteAbrv,
            sectNm = sectNm,
            term = term,
            routeType = routeType,
            nextBus = nextBus,
            vehId1 = vehId1,
            sectOrd1 = sectOrd1,
            stationNm1 = stationNm1,
            traTime1 = traTime1,
            isArrive1 = isArrive1,
            isLast1 = isLast1,
            busType1 = busType1,
            vehId2 = vehId2,
            sectOrd2 = sectOrd2,
            stationNm2 = stationNm2,
            traTime2 = traTime2,
            isArrive2 = isArrive2,
            isLast2 = isLast2,
            busType2 = busType2,
            adirection = adirection,
            arrmsg1 = arrmsg1,
            arrmsg2 = arrmsg2,
            arrmsgSec1 = arrmsgSec1,
            arrmsgSec2 = arrmsgSec2
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

    private fun BusLineItem.toRangeSearchItem():RangeSearchItem{
        return RangeSearchItem(
            rtNm = rtNm
        )
    }

}

data class MapScreenUiState(
    val isSmallMapEnabled: Boolean = false,
    val zoomControlsEnabled: Boolean = false,
    val mapToolbarEnabled:Boolean = false,
    val rotationGesturesEnabled:Boolean = false,
    val markerInfos:List<MarkerInfo> = emptyList()
)