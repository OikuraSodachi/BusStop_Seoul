package com.todokanai.domain.dataclass

data class StationArriveItem(
    val stId: Long,          // 정류소 고유 ID

    val stNm: String,          // 정류소명

    val arsId: Long,         // 정류소 번호 (5자리)

    val busRouteId: Long,    // 노선 ID

    val rtNm: String,          // 노선명

    val busRouteAbrv: String? = null,  // 노선 약어

    val sectNm: String? = null,        // 구간명

    val gpsX: String? = null,          // X 좌표 (경도)

    val gpsY: String? = null,          // Y 좌표 (위도)

    val stationTp: Int? = null,     // 정류소 타입

    val firstTm: String? = null,       // 첫차 시간

    val lastTm: String? = null,        // 막차 시간

    val term: String? = null,          // 배차 간격

    val routeType: String? = null,     // 노선 유형

    val nextBus: String? = null,       // 다음 버스 정보

    val staOrd: String? = null,        // 정류소 순번

    val vehId1: String? = null,        // 첫번째 도착예정버스 ID

    val sectOrd1: String? = null,      // 첫번째 도착예정버스 구간 순번

    val stationNm1: String? = null,    // 첫번째 도착예정버스 현재 위치

    val traTime1: String? = null,      // 첫번째 도착예정버스 도착 예상 시간(초)

    val traSpd1: String? = null,       // 첫번째 도착예정버스 속도

    val isArrive1: String? = null,     // 첫번째 도착예정버스 도착 구분

    val repTm1: String? = null,        // 첫번째 도착예정버스 최종 보고 시간

    val isLast1: String? = null,       // 첫번째 도착예정버스 막차 여부

    val busType1: String? = null,      // 첫번째 도착예정버스 차량 유형

    val vehId2: String? = null,        // 두번째 도착예정버스 ID

    val sectOrd2: String? = null,      // 두번째 도착예정버스 구간 순번

    val stationNm2: String? = null,    // 두번째 도착예정버스 현재 위치

    val traTime2: String? = null,      // 두번째 도착예정버스 도착 예상 시간(초)

    val traSpd2: String? = null,       // 두번째 도착예정버스 속도

    val isArrive2: String? = null,     // 두번째 도착예정버스 도착 구분

    val isLast2: String? = null,       // 두번째 도착예정버스 막차 여부

    val busType2: String? = null,      // 두번째 도착예정버스 차량 유형

    val adirection: String,    // 방면

    val arrmsg1: String? = null,       // 첫번째 도착예정 메시지

    val arrmsg2: String? = null,       // 두번째 도착예정 메시지

    val arrmsgSec1: String? = null,    // 첫번째 도착예정 메시지 (초 포함)

    val arrmsgSec2: String? = null,    // 두번째 도착예정 메시지 (초 포함)

    val nxtStn: String? = null,        // 다음 정류장 이름

    val rerdieDiv1: String? = null,

    val rerdieDiv2: String? = null,

    val rerideNum1: String? = null,

    val rerideNum2: String? = null,

    val isFullFlag1: String? = null,   // 첫번째 버스 만차 여부

    val isFullFlag2: String? = null,   // 두번째 버스 만차 여부

    val deTourAt: String? = null,      // 우회 여부

    val congestion1: String? = null,   // 첫번째 버스 혼잡도

    val congestion2: String? = null,   // 두번째 버스 혼잡도

    val remndrNmpr1: String? = null,

    val remndrNmpr2: String? = null
)