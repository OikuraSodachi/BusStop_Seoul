package com.todokanai.data.retrofit.stationinfo.responsetype.bustimebystation

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "ServiceResult", strict = false)
data class BusTimeByStationMsgBody(
    @field:Element(name = "itemList", required = false)
    var itemList: List<BusTimeByStationItem>? = null
)
