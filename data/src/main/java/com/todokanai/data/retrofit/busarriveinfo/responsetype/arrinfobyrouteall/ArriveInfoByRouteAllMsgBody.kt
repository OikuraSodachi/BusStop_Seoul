package com.todokanai.data.retrofit.busarriveinfo.responsetype.arrinfobyrouteall

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "msgBody", strict = false)
data class ArriveInfoByRouteAllMsgBody(
    @field:ElementList(inline = true, name = "itemList", required = false)
    var itemList: List<ArriveInfoByRouteAllItem>? = null
)
