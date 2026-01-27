package com.todokanai.data.retrofit.stationinfo.responsetype.stationarrive

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "msgBody", strict = false)
data class StationArriveMsgBody(
    @field:ElementList(inline = true, entry = "itemList", required = false)
    var itemList: List<StationArriveItem>? = null
)