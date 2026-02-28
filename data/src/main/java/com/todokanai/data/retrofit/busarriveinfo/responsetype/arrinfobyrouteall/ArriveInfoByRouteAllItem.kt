package com.todokanai.data.retrofit.busarriveinfo.responsetype.arrinfobyrouteall

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * @param stId 정류소 ID
 * @param stNm 정류소명
 * @param arsId 정류소 고유번호
 * @param staOrd 순번
 * @param busRouteId 노선 ID
 * @param rtNm 노선명
 * @param arrmsg1 첫번째 도착 메시지 (예: "5분후[3번째 전]")
 * @param vehId1 첫번째 버스 ID
 * @param busType1 첫번째 버스 타입 (0:일반, 1:저상)
 * @param plainNo1 첫번째 버스 차량번호
 * @param arrmsg2 두번째 도착 메시지
 * @param vehId2 두번째 버스 ID
 * @param busType2 두번째 버스 타입
 * @param plainNo2 두번째 버스 차량번호
 * @param mkTm 데이터 생성시각
 * @param routeType 노선 타입
 * @param term 배차간격
 * @param firstTm 첫차시간
 * @param lastTm 막차시간 */
@Root(name = "itemList", strict = false)
data class ArriveInfoByRouteAllItem(
    //--- 기본 정보 ---
    @field:Element(name = "stId", required = false)
    var stId: String? = null,           // 정류소 ID

    @field:Element(name = "stNm", required = false)
    var stNm: String? = null,           // 정류소명

    @field:Element(name = "arsId", required = false)
    var arsId: String? = null,          // 정류소 고유번호

    @field:Element(name = "staOrd", required = false)
    var staOrd: String? = null,         // 순번

    @field:Element(name = "busRouteId", required = false)
    var busRouteId: String? = null,     // 노선 ID

    @field:Element(name = "rtNm", required = false)
    var rtNm: String? = null,           // 노선명

    //--- 첫번째 도착 예정 버스 정보 ---
    @field:Element(name = "arrmsg1", required = false)
    var arrmsg1: String? = null,        // 첫번째 도착 메시지 (예: "5분후[3번째 전]")

    @field:Element(name = "vehId1", required = false)
    var vehId1: String? = null,         // 첫번째 버스 ID

    @field:Element(name = "busType1", required = false)
    var busType1: String? = null,       // 첫번째 버스 타입 (0:일반, 1:저상)

    @field:Element(name = "plainNo1", required = false)
    var plainNo1: String? = null,       // 첫번째 버스 차량번호

    //--- 두번째 도착 예정 버스 정보 ---
    @field:Element(name = "arrmsg2", required = false)
    var arrmsg2: String? = null,        // 두번째 도착 메시지

    @field:Element(name = "vehId2", required = false)
    var vehId2: String? = null,         // 두번째 버스 ID

    @field:Element(name = "busType2", required = false)
    var busType2: String? = null,       // 두번째 버스 타입

    @field:Element(name = "plainNo2", required = false)
    var plainNo2: String? = null,       // 두번째 버스 차량번호

    //--- 기타 정보 ---
    @field:Element(name = "mkTm", required = false)
    var mkTm: String? = null,           // 데이터 생성시각

    @field:Element(name = "routeType", required = false)
    var routeType: String? = null,      // 노선 타입

    @field:Element(name = "term", required = false)
    var term: String? = null,           // 배차간격

    @field:Element(name = "firstTm", required = false)
    var firstTm: String? = null,        // 첫차시간

    @field:Element(name = "lastTm", required = false)
    var lastTm: String? = null          // 막차시간
)
