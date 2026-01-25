package com.todokanai.data.retrofit.busarriveinfo.responsetype.arrinfobyrouteall

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
data class ArriveInfoByRouteAllItem(
    //--- 기본 정보 ---
    @field:Element(name = "stId", required = false)
    val stId: String? = null,           // 정류소 ID

    @field:Element(name = "stNm", required = false)
    val stNm: String? = null,           // 정류소명

    @field:Element(name = "arsId", required = false)
    val arsId: String? = null,          // 정류소 고유번호

    @field:Element(name = "staOrd", required = false)
    val staOrd: String? = null,         // 순번

    @field:Element(name = "busRouteId", required = false)
    val busRouteId: String? = null,     // 노선 ID

    @field:Element(name = "rtNm", required = false)
    val rtNm: String? = null,           // 노선명

    //--- 첫번째 도착 예정 버스 정보 ---
    @field:Element(name = "arrmsg1", required = false)
    val arrmsg1: String? = null,        // 첫번째 도착 메시지 (예: "5분후[3번째 전]")

    @field:Element(name = "vehId1", required = false)
    val vehId1: String? = null,         // 첫번째 버스 ID

    @field:Element(name = "busType1", required = false)
    val busType1: String? = null,       // 첫번째 버스 타입 (0:일반, 1:저상)

    @field:Element(name = "plainNo1", required = false)
    val plainNo1: String? = null,       // 첫번째 버스 차량번호

    //--- 두번째 도착 예정 버스 정보 ---
    @field:Element(name = "arrmsg2", required = false)
    val arrmsg2: String? = null,        // 두번째 도착 메시지

    @field:Element(name = "vehId2", required = false)
    val vehId2: String? = null,         // 두번째 버스 ID

    @field:Element(name = "busType2", required = false)
    val busType2: String? = null,       // 두번째 버스 타입

    @field:Element(name = "plainNo2", required = false)
    val plainNo2: String? = null,       // 두번째 버스 차량번호

    //--- 기타 정보 ---
    @field:Element(name = "mkTm", required = false)
    val mkTm: String? = null,           // 데이터 생성시각

    @field:Element(name = "routeType", required = false)
    val routeType: String? = null,      // 노선 타입

    @field:Element(name = "term", required = false)
    val term: String? = null,           // 배차간격

    @field:Element(name = "firstTm", required = false)
    val firstTm: String? = null,        // 첫차시간

    @field:Element(name = "lastTm", required = false)
    val lastTm: String? = null          // 막차시간
)
