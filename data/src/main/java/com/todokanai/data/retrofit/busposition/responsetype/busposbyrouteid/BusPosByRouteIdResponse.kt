package com.todokanai.data.retrofit.busposition.responsetype.busposbyrouteid

import com.todokanai.data.retrofit.header.MsgHeader
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "ServiceResult", strict = false)
data class BusPosByRouteIdResponse(

    @field:Element(name = "msgHeader", required = false)
    val msgHeader: MsgHeader? = null,
    @field:Element(name = "msgBody", required = false)
    val msgBody: BusPosByRouteIdMsgBody? = null

)
