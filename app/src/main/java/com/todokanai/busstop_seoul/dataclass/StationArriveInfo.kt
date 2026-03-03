package com.todokanai.busstop_seoul.dataclass

import androidx.compose.runtime.Stable

/** data class for [com.todokanai.busstop_seoul.compose.holder.StationArriveHolder]**/
@Stable
data class StationArriveInfo(

    val busRouteId: Long,    // 노선 ID

    val rtNm: String,          // 노선명

    val busRouteAbrv: String? = null,  // 노선 약어

    val sectNm: String? = null,        // 구간명

    val term: String? = null,          // 배차 간격

    val routeType: String? = null,     // 노선 유형

    val nextBus: String? = null,       // 다음 버스 정보

    val vehId1: String? = null,        // 첫번째 도착예정버스 ID

    val sectOrd1: String? = null,      // 첫번째 도착예정버스 구간 순번

    val stationNm1: String? = null,    // 첫번째 도착예정버스 현재 위치

    val traTime1: String? = null,      // 첫번째 도착예정버스 도착 예상 시간(초)

    val isArrive1: String? = null,     // 첫번째 도착예정버스 도착 구분

    val isLast1: String? = null,       // 첫번째 도착예정버스 막차 여부

    val busType1: String? = null,      // 첫번째 도착예정버스 차량 유형

    val vehId2: String? = null,        // 두번째 도착예정버스 ID

    val sectOrd2: String? = null,      // 두번째 도착예정버스 구간 순번

    val stationNm2: String? = null,    // 두번째 도착예정버스 현재 위치

    val traTime2: String? = null,      // 두번째 도착예정버스 도착 예상 시간(초)

    val isArrive2: String? = null,     // 두번째 도착예정버스 도착 구분

    val isLast2: String? = null,       // 두번째 도착예정버스 막차 여부

    val busType2: String? = null,      // 두번째 도착예정버스 차량 유형

    val adirection: String,    // 방면

    val arrmsg1: String? = null,       // 첫번째 도착예정 메시지

    val arrmsg2: String? = null,       // 두번째 도착예정 메시지

    val arrmsgSec1: String? = null,    // 첫번째 도착예정 메시지 (초 포함)

    val arrmsgSec2: String? = null,    // 두번째 도착예정 메시지 (초 포함)

)