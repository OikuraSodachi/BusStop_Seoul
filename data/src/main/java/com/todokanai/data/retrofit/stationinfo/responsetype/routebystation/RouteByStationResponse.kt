package com.todokanai.data.retrofit.stationinfo.responsetype.routebystation

import com.todokanai.data.retrofit.header.MsgHeader
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "ServiceResult", strict = false)
data class RouteByStationResponse(
    @field:Element(name = "msgHeader", required = false)
    var msgHeader: MsgHeader? = null,

    @field:Element(name = "msgBody", required = false)
    var msgBody: RouteByStationMsgBody? = null
)