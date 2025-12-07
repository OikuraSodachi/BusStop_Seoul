package com.todokanai.data.retrofit.stationarrrivetest

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "msgBody", strict = false)
data class MsgBody(
    @field:ElementList(inline = true, entry = "itemList", required = false)
    var itemList: List<BusArrivalResponse>? = null
)