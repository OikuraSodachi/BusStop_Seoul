package com.todokanai.data.retrofit.stationinfo.responsetype.routebystation

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "msgBody", strict = false)
data class RouteByStationMsgBody(
    @field:ElementList(inline = true, entry = "itemList", required = false)
    val itemList: List<RouteByStationItem>? = null
)
