package com.todokanai.data.retrofit.stationbyname

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
data class StationByNameItem(
    @field:Element(name = "stId", required = false)
    var stId: String? = null,          // 정류소 ID

    @field:Element(name = "stNm", required = false)
    var stNm: String? = null,          // 정류소 명

    @field:Element(name = "arsId", required = false)
    var arsId: String? = null,         // 정류소 고유 번호 (5자리)

    @field:Element(name = "tmX", required = false)
    var tmX: String? = null,           // 경도 (Longitude)

    @field:Element(name = "tmY", required = false)
    var tmY: String? = null,           // 위도 (Latitude)

    @field:Element(name = "posX", required = false)
    var posX: String? = null,          // 좌표 X (GRS80)

    @field:Element(name = "posY", required = false)
    var posY: String? = null           // 좌표 Y (GRS80)
)