package com.todokanai.data.retrofit.busarriveinfo.responsetype.arrinfobyrouteall

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "msgBody", strict = false)
data class ArriveInfoByRouteAllMsgBody(
    @field:Element(name = "itemList", required = false)
    val itemList: List<ArriveInfoByRouteAllItem>? = null
)
