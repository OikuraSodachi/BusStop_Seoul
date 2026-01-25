package com.todokanai.domain.response

data class ArriveInfoByRouteAllItem(
    //--- 기본 정보 ---
    val stId: String? = null,           // 정류소 ID

    val stNm: String? = null,           // 정류소명

    val arsId: String? = null,          // 정류소 고유번호

    val staOrd: String? = null,         // 순번

    val busRouteId: String? = null,     // 노선 ID

    val rtNm: String? = null,           // 노선명

    //--- 첫번째 도착 예정 버스 정보 ---
    val arrmsg1: String? = null,        // 첫번째 도착 메시지 (예: "5분후[3번째 전]")

    val vehId1: String? = null,         // 첫번째 버스 ID

    val busType1: String? = null,       // 첫번째 버스 타입 (0:일반, 1:저상)

    val plainNo1: String? = null,       // 첫번째 버스 차량번호

    //--- 두번째 도착 예정 버스 정보 ---
    val arrmsg2: String? = null,        // 두번째 도착 메시지

    val vehId2: String? = null,         // 두번째 버스 ID

    val busType2: String? = null,       // 두번째 버스 타입

    val plainNo2: String? = null,       // 두번째 버스 차량번호

    //--- 기타 정보 ---
    val mkTm: String? = null,           // 데이터 생성시각

    val routeType: String? = null,      // 노선 타입

    val term: String? = null,           // 배차간격

    val firstTm: String? = null,        // 첫차시간

    val lastTm: String? = null          // 막차시간
)
