package com.todokanai.data.retrofit.stationinfo.responsetype.stationbyposition

import com.todokanai.data.retrofit.header.MsgHeader
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "ServiceResult", strict = false)
data class StationByPositionResponse(
    @field:Element(name = "msgHeader", required = false)
    var msgHeader: MsgHeader? = null,

    @field:Element(name = "msgBody", required = false)
    var msgBody: StationByPositionMsgBody? = null
)
