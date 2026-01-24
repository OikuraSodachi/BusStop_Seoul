package com.todokanai.data.retrofit.stationinfo.responsetype.routebystation

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
data class RouteByStationItem(
    @field:Element(name = "busRouteId", required = false)
    val busRouteId: String? = null,    // 노선 ID

    @field:Element(name = "busRouteNm", required = false)
    val busRouteNm: String? = null,    // 노선명 (예: 7211)

    @field:Element(name = "busRouteAbrv", required = false)
    val busRouteAbrv: String? = null,  // 노선 약어 (예: 7211)

    @field:Element(name = "busRouteType", required = false)
    val busRouteType: String? = null,  // 노선 유형

    @field:Element(name = "stBegin", required = false)
    val stBegin: String? = null,       // 기점

    @field:Element(name = "stEnd", required = false)
    val stEnd: String? = null,         // 종점

    @field:Element(name = "term", required = false)
    val term: String? = null,          // 배차 간격

    @field:Element(name = "firstBusTm", required = false)
    val firstBusTm: String? = null,    // 첫차 시간

    @field:Element(name = "lastBusTm", required = false)
    val lastBusTm: String? = null      // 막차 시간
)