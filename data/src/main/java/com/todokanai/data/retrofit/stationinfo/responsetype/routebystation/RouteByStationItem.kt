package com.todokanai.data.retrofit.stationinfo.responsetype.routebystation

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
data class RouteByStationItem(
    @field:Element(name = "busRouteId", required = false)
    var busRouteId: String? = null,    // 노선 ID

    @field:Element(name = "busRouteNm", required = false)
    var busRouteNm: String? = null,    // 노선명 (예: 7211)

    @field:Element(name = "busRouteAbrv", required = false)
    var busRouteAbrv: String? = null,  // 노선 약어 (예: 7211)

    @field:Element(name = "busRouteType", required = false)
    var busRouteType: String? = null,  // 노선 유형

    @field:Element(name = "stBegin", required = false)
    var stBegin: String? = null,       // 기점

    @field:Element(name = "stEnd", required = false)
    var stEnd: String? = null,         // 종점

    @field:Element(name = "term", required = false)
    var term: String? = null,          // 배차 간격

    @field:Element(name = "firstBusTm", required = false)
    var firstBusTm: String? = null,    // 첫차 시간

    @field:Element(name = "lastBusTm", required = false)
    var lastBusTm: String? = null      // 막차 시간
)