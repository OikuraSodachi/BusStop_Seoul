package com.todokanai.domain.dataclass

/**
 * @param busRouteId 노선 ID
 * @param rtNm 노선명
 * @param routeAbrv 노선 약어
 * @param routeType 노선 유형
 * @param stBegin 기점
 * @param stEnd 종점
 * @param term 배차 간격
 * @param firstBusTm 첫차 시간
 * @param lastBusTm 막차 시간
 */
data class BusLineItem(
    val busRouteId: Long,
    val rtNm: String,
    val routeAbrv: String? = null,
    val routeType: String? = null,
    val stBegin: String? = null,
    val stEnd: String? = null,
    val term: String,
    val firstBusTm: String,
    val lastBusTm: String
)
