package com.todokanai.data.retrofit.busposition.responsetype.busposbyrouteid

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "itemList", strict = false)
data class BusPosByRouteIdItem(

    @field:Element(name = "vehId", required = false)
    val vehId: String? = null,          // 버스 ID

    @field:Element(name = "plainNo", required = false)
    val plainNo: String? = null,        // 차량 번호

    @field:Element(name = "busType", required = false)
    val busType: String? = null,        // 버스 타입 (0:일반, 1:저상, 2:굴절)

    @field:Element(name = "lastStnId", required = false)
    val lastStnId: String? = null,      // 최근 정류소 ID

    @field:Element(name = "congetion", required = false)
    val congetion: String? = null,      // 혼잡도 (3:여유, 4:보통, 5:혼잡)

    @field:Element(name = "sectOrd", required = false)
    val sectOrd: String? = null,        // 구간 순서

    @field:Element(name = "gpsX", required = false)
    val gpsX: String? = null,           // 경도 (WGS84)

    @field:Element(name = "gpsY", required = false)
    val gpsY: String? = null,           // 위도 (WGS84)

    @field:Element(name = "isFullFlag", required = false)
    val isFullFlag: String? = null,     // 만차 여부 (0:여유, 1:만차)

    @field:Element(name = "stopFlag", required = false)
    val stopFlag: String? = null        // 정류소 정차 여부 (0:운행중, 1:정차)
)