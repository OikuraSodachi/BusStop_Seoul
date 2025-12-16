package com.todokanai.data.retrofit.stationbyname

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "msgBody", strict = false)
data class StationMsgBody(
    @field:ElementList(inline = true, entry = "itemList", required = false)
    var itemList: List<StationItem>? = null
)