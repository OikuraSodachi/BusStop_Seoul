package com.todokanai.data.retrofit.busposition.responsetype.busposbyrouteid

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "msgBody", strict = false)
data class BusPosByRouteIdMsgBody(
    @field:Element(name = "itemList", required = false)
    val itemList: List<BusPosByRouteIdItem>? = null
)