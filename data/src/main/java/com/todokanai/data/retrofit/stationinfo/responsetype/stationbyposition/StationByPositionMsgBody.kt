package com.todokanai.data.retrofit.stationinfo.responsetype.stationbyposition

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "msgBody", strict = false)
data class StationByPositionMsgBody(
    @field:ElementList(inline = true, entry = "itemList", required = false)
    val itemList: List<StationByPositionItem>? = null
)
