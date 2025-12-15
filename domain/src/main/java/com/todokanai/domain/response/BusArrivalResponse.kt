package com.todokanai.domain.response

data class BusArrivalResponse(
    var stId: String? = null,          // 정류소 고유 ID

    var stNm: String? = null,          // 정류소명

    var arsId: String? = null,         // 정류소 번호 (5자리)

    var busRouteId: String? = null,    // 노선 ID

    var rtNm: String? = null,          // 노선명

    var busRouteAbrv: String? = null,  // 노선 약어

    var sectNm: String? = null,        // 구간명

    var gpsX: String? = null,          // X 좌표 (경도)

    var gpsY: String? = null,          // Y 좌표 (위도)

    var stationTp: String? = null,     // 정류소 타입

    var firstTm: String? = null,       // 첫차 시간

    var lastTm: String? = null,        // 막차 시간

    var term: String? = null,          // 배차 간격

    var routeType: String? = null,     // 노선 유형

    var nextBus: String? = null,       // 다음 버스 정보

    var staOrd: String? = null,        // 정류소 순번

    var vehId1: String? = null,        // 첫번째 도착예정버스 ID

    var sectOrd1: String? = null,      // 첫번째 도착예정버스 구간 순번

    var stationNm1: String? = null,    // 첫번째 도착예정버스 현재 위치

    var traTime1: String? = null,      // 첫번째 도착예정버스 도착 예상 시간(초)

    var traSpd1: String? = null,       // 첫번째 도착예정버스 속도

    var isArrive1: String? = null,     // 첫번째 도착예정버스 도착 구분

    var repTm1: String? = null,        // 첫번째 도착예정버스 최종 보고 시간

    var isLast1: String? = null,       // 첫번째 도착예정버스 막차 여부

    var busType1: String? = null,      // 첫번째 도착예정버스 차량 유형

    var vehId2: String? = null,        // 두번째 도착예정버스 ID

    var sectOrd2: String? = null,      // 두번째 도착예정버스 구간 순번

    var stationNm2: String? = null,    // 두번째 도착예정버스 현재 위치

    var traTime2: String? = null,      // 두번째 도착예정버스 도착 예상 시간(초)

    var traSpd2: String? = null,       // 두번째 도착예정버스 속도

    var isArrive2: String? = null,     // 두번째 도착예정버스 도착 구분

    var isLast2: String? = null,       // 두번째 도착예정버스 막차 여부

    var busType2: String? = null,      // 두번째 도착예정버스 차량 유형

    var adirection: String? = null,    // 방면

    var arrmsg1: String? = null,       // 첫번째 도착예정 메시지

    var arrmsg2: String? = null,       // 두번째 도착예정 메시지

    var arrmsgSec1: String? = null,    // 첫번째 도착예정 메시지 (초 포함)

    var arrmsgSec2: String? = null,    // 두번째 도착예정 메시지 (초 포함)

    var nxtStn: String? = null,        // 다음 정류장 이름

    var rerdieDiv1: String? = null,

    var rerdieDiv2: String? = null,

    var rerideNum1: String? = null,

    var rerideNum2: String? = null,

    var isFullFlag1: String? = null,   // 첫번째 버스 만차 여부

    var isFullFlag2: String? = null,   // 두번째 버스 만차 여부

    var deTourAt: String? = null,      // 우회 여부

    var congestion1: String? = null,   // 첫번째 버스 혼잡도

    var congestion2: String? = null,   // 두번째 버스 혼잡도

    var remndrNmpr1: String? = null,

    var remndrNmpr2: String? = null
)