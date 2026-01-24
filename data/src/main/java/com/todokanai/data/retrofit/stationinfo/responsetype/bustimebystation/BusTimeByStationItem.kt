package com.todokanai.data.retrofit.stationinfo.responsetype.bustimebystation

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
data class BusTimeByStationItem(
    @field:Element(name = "busRouteId", required = false)
    val busRouteId: String? = null,    // 노선 ID

    @field:Element(name = "busRouteNm", required = false)
    val busRouteNm: String? = null,    // 노선명 (예: 7211)

    @field:Element(name = "busRouteAbrv", required = false)
    val busRouteAbrv: String? = null,  // 노선 약어 (예: 7211)

    @field:Element(name = "arsId", required = false)
    val arsId: String? = null,         // 정류소 번호

    @field:Element(name = "stationNm", required = false)
    val stationNm: String? = null,     // 정류소 이름

    @field:Element(name = "firstBusTm", required = false)
    val firstBusTm: String? = null,    // 첫차 시간

    @field:Element(name = "lastBusTm", required = false)
    val lastBusTm: String? = null      // 막차 시간
)
