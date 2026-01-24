package com.todokanai.data.retrofit.stationinfo.responsetype.stationbyname

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
data class StationByNameItem(
    @field:Element(name = "stId", required = false)
    val stId: String? = null,          // 정류소 ID

    @field:Element(name = "stNm", required = false)
    val stNm: String? = null,          // 정류소 명

    @field:Element(name = "arsId", required = false)
    val arsId: String? = null,         // 정류소 고유 번호 (5자리)

    @field:Element(name = "tmX", required = false)
    val tmX: String? = null,           // 경도 (Longitude)

    @field:Element(name = "tmY", required = false)
    val tmY: String? = null,           // 위도 (Latitude)

    @field:Element(name = "posX", required = false)
    val posX: String? = null,          // 좌표 X (GRS80)

    @field:Element(name = "posY", required = false)
    val posY: String? = null           // 좌표 Y (GRS80)
)