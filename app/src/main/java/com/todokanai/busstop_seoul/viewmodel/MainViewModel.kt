package com.todokanai.busstop_seoul.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.MapUiSettings
import com.todokanai.busstop_seoul.Constants.MAP_MARKER_MINIMUM_RADIUS
import com.todokanai.busstop_seoul.dataclass.LineInfo
import com.todokanai.busstop_seoul.dataclass.MarkerInfo
import com.todokanai.busstop_seoul.dataclass.StationArriveInfo
import com.todokanai.busstop_seoul.dataclass.StationInfo
import com.todokanai.busstop_seoul.interfaces.compose.MainMapInterface
import com.todokanai.busstop_seoul.interfaces.compose.MainScreenInterface
import com.todokanai.domain.BusUseCase
import com.todokanai.domain.MapUseCase
import com.todokanai.domain.response.StationArriveItem
import com.todokanai.domain.response.StationItem
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
    private val busUseCase: BusUseCase
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
            return busUseCase.getArriveInfos(key).map{
                it.toStationArriveInfo()
            }
        }
        override suspend fun getVisibleStation(
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

        override suspend fun getLineInfos(routeId: Long): List<LineInfo> {
            val stList = mutableListOf<String>()    // 노선이 지나는 정류장 목록

            val response = busUseCase.getArriveInfoByRouteAll(routeId)
            // Todo: stList 값 가져오기

            fun busPositionCheck(stNm:String):List<String>{
                // Todo: response 로부터, 해당 정류소에 위차한 버스 목록 가져오기. REST API 호출 횟수 최적화에 주의.
                return emptyList()
            }
            val result = stList.map{
                LineInfo(
                    stNm = it,
                    busInfo = busPositionCheck(it)
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

    // Todo: tmX, tmY 값 null 케이스 제거
    private fun StationItem.toStationInfo(): StationInfo {
        return StationInfo(
            stId = stId.toString(),
            stNm = stNm.toString(),
            arsId = arsId.toString(),
            tmX = tmX?: 0.0,
            tmY = tmY?: 0.0,
            posX = posX,
            posY = posY,
            stationTp = stationTp
        )
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