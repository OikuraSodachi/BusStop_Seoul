package com.todokanai.data.retrofit.stationbyname

import com.todokanai.domain.response.StationItem
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
data class StationItem(
    @field:Element(name = "stId", required = true)
    var stId: String,          // 정류소 ID

    @field:Element(name = "stNm", required = true)
    var stNm: String,          // 정류소 명

    @field:Element(name = "arsId", required = true)
    var arsId: String,         // 정류소 고유 번호 (5자리)

    @field:Element(name = "tmX", required = true)
    var tmX: Double,           // 경도 (Longitude)

    @field:Element(name = "tmY", required = true)
    var tmY: Double,           // 위도 (Latitude)

    @field:Element(name = "posX", required = false)
    var posX: String? = null,          // 좌표 X (GRS80)

    @field:Element(name = "posY", required = false)
    var posY: String? = null           // 좌표 Y (GRS80)
){
    fun convert(): StationItem{
        return StationItem(
            stId,
            stNm,
            arsId,
            tmX,
            tmY,
            posX,
            posY
        )
    }
}