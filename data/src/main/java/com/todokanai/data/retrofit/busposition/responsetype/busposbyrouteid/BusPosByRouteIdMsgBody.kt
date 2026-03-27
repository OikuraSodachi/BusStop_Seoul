package com.todokanai.data.retrofit.busposition.responsetype.busposbyrouteid

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "msgBody", strict = false)
data class BusPosByRouteIdMsgBody(
    @field:ElementList(name = "itemList", required = false, inline = true)
    var itemList: List<BusPosByRouteIdItem>? = null
)