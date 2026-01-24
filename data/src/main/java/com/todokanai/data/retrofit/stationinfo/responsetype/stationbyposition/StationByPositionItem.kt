package com.todokanai.data.retrofit.stationinfo.responsetype.stationbyposition

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
data class StationByPositionItem(
    @field:Element(name = "stationId", required = false) // 'stationId'로 변경 (XML 기준)
    val stId: String? = null,

    @field:Element(name = "stationNm", required = false) // 'stationNm'으로 변경 (XML 기준)
    val stNm: String? = null,

    @field:Element(name = "arsId", required = false)
    val arsId: String? = null,

    @field:Element(name = "gpsX", required = false)     // gpsX, gpsY로 변경
    val tmX: String? = null,

    @field:Element(name = "gpsY", required = false)
    val tmY: String? = null,

    @field:Element(name = "posX", required = false)
    val posX: String? = null,

    @field:Element(name = "posY", required = false)
    val posY: String? = null,

    @field:Element(name = "dist", required = false)
    val dist: String? = null,                          // 거리 (단위: 미터)

    @field:Element(name = "stationTp", required = false)
    val stationTp: String? = null                      // 정류소 타입
)