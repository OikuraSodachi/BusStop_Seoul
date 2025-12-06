package com.todokanai.domain.stationarrrivetest

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
data class BusArrivalResponse(
    @field:Element(name = "stId", required = false)
    var stId: String? = null,          // 정류소 고유 ID

    @field:Element(name = "stNm", required = false)
    var stNm: String? = null,          // 정류소명

    @field:Element(name = "arsId", required = false)
    var arsId: String? = null,         // 정류소 번호 (5자리)

    @field:Element(name = "busRouteId", required = false)
    var busRouteId: String? = null,    // 노선 ID

    @field:Element(name = "rtNm", required = false)
    var rtNm: String? = null,          // 노선명

    @field:Element(name = "busRouteAbrv", required = false)
    var busRouteAbrv: String? = null,  // 노선 약어

    @field:Element(name = "sectNm", required = false)
    var sectNm: String? = null,        // 구간명

    @field:Element(name = "gpsX", required = false)
    var gpsX: String? = null,          // X 좌표 (경도)

    @field:Element(name = "gpsY", required = false)
    var gpsY: String? = null,          // Y 좌표 (위도)

    @field:Element(name = "stationTp", required = false)
    var stationTp: String? = null,     // 정류소 타입

    @field:Element(name = "firstTm", required = false)
    var firstTm: String? = null,       // 첫차 시간

    @field:Element(name = "lastTm", required = false)
    var lastTm: String? = null,        // 막차 시간

    @field:Element(name = "term", required = false)
    var term: String? = null,          // 배차 간격

    @field:Element(name = "routeType", required = false)
    var routeType: String? = null,     // 노선 유형

    @field:Element(name = "nextBus", required = false)
    var nextBus: String? = null,       // 다음 버스 정보

    @field:Element(name = "staOrd", required = false)
    var staOrd: String? = null,        // 정류소 순번

    @field:Element(name = "vehId1", required = false)
    var vehId1: String? = null,        // 첫번째 도착예정버스 ID

    @field:Element(name = "sectOrd1", required = false)
    var sectOrd1: String? = null,      // 첫번째 도착예정버스 구간 순번

    @field:Element(name = "stationNm1", required = false)
    var stationNm1: String? = null,    // 첫번째 도착예정버스 현재 위치

    @field:Element(name = "traTime1", required = false)
    var traTime1: String? = null,      // 첫번째 도착예정버스 도착 예상 시간(초)

    @field:Element(name = "traSpd1", required = false)
    var traSpd1: String? = null,       // 첫번째 도착예정버스 속도

    @field:Element(name = "isArrive1", required = false)
    var isArrive1: String? = null,     // 첫번째 도착예정버스 도착 구분

    @field:Element(name = "repTm1", required = false)
    var repTm1: String? = null,        // 첫번째 도착예정버스 최종 보고 시간

    @field:Element(name = "isLast1", required = false)
    var isLast1: String? = null,       // 첫번째 도착예정버스 막차 여부

    @field:Element(name = "busType1", required = false)
    var busType1: String? = null,      // 첫번째 도착예정버스 차량 유형

    @field:Element(name = "vehId2", required = false)
    var vehId2: String? = null,        // 두번째 도착예정버스 ID

    @field:Element(name = "sectOrd2", required = false)
    var sectOrd2: String? = null,      // 두번째 도착예정버스 구간 순번

    @field:Element(name = "stationNm2", required = false)
    var stationNm2: String? = null,    // 두번째 도착예정버스 현재 위치

    @field:Element(name = "traTime2", required = false)
    var traTime2: String? = null,      // 두번째 도착예정버스 도착 예상 시간(초)

    @field:Element(name = "traSpd2", required = false)
    var traSpd2: String? = null,       // 두번째 도착예정버스 속도

    @field:Element(name = "isArrive2", required = false)
    var isArrive2: String? = null,     // 두번째 도착예정버스 도착 구분

    @field:Element(name = "isLast2", required = false)
    var isLast2: String? = null,       // 두번째 도착예정버스 막차 여부

    @field:Element(name = "busType2", required = false)
    var busType2: String? = null,      // 두번째 도착예정버스 차량 유형

    @field:Element(name = "adirection", required = false)
    var adirection: String? = null,    // 방면

    @field:Element(name = "arrmsg1", required = false)
    var arrmsg1: String? = null,       // 첫번째 도착예정 메시지

    @field:Element(name = "arrmsg2", required = false)
    var arrmsg2: String? = null,       // 두번째 도착예정 메시지

    @field:Element(name = "arrmsgSec1", required = false)
    var arrmsgSec1: String? = null,    // 첫번째 도착예정 메시지 (초 포함)

    @field:Element(name = "arrmsgSec2", required = false)
    var arrmsgSec2: String? = null,    // 두번째 도착예정 메시지 (초 포함)

    @field:Element(name = "nxtStn", required = false)
    var nxtStn: String? = null,        // 다음 정류장 이름

    @field:Element(name = "rerdieDiv1", required = false)
    var rerdieDiv1: String? = null,

    @field:Element(name = "rerdieDiv2", required = false)
    var rerdieDiv2: String? = null,

    @field:Element(name = "rerideNum1", required = false)
    var rerideNum1: String? = null,

    @field:Element(name = "rerideNum2", required = false)
    var rerideNum2: String? = null,

    @field:Element(name = "isFullFlag1", required = false)
    var isFullFlag1: String? = null,   // 첫번째 버스 만차 여부

    @field:Element(name = "isFullFlag2", required = false)
    var isFullFlag2: String? = null,   // 두번째 버스 만차 여부

    @field:Element(name = "deTourAt", required = false)
    var deTourAt: String? = null,      // 우회 여부

    @field:Element(name = "congestion1", required = false)
    var congestion1: String? = null,   // 첫번째 버스 혼잡도

    @field:Element(name = "congestion2", required = false)
    var congestion2: String? = null,   // 두번째 버스 혼잡도

    @field:Element(name = "remndrNmpr1", required = false)
    var remndrNmpr1: String? = null,

    @field:Element(name = "remndrNmpr2", required = false)
    var remndrNmpr2: String? = null
)
