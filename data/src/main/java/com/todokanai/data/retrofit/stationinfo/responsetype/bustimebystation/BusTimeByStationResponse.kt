package com.todokanai.data.retrofit.stationinfo.responsetype.bustimebystation

import com.todokanai.data.retrofit.header.MsgHeader
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "ServiceResult", strict = false)
data class BusTimeByStationResponse(
    @field:Element(name = "msgHeader", required = false)
    val msgHeader: MsgHeader? = null,

    @field:Element(name = "msgBody", required = false)
    val msgBody: BusTimeByStationMsgBody? = null
)
