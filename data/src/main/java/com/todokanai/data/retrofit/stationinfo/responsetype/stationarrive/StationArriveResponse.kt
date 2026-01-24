package com.todokanai.data.retrofit.stationinfo.responsetype.stationarrive

import com.todokanai.data.retrofit.header.MsgHeader
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "ServiceResult", strict = false)
data class StationArriveResponse(
    @field:Element(name = "msgHeader", required = false)
    val msgHeader: MsgHeader? = null,

    @field:Element(name = "msgBody", required = false)
    val stationArriveMsgBody: StationArriveMsgBody? = null
)