package com.todokanai.domain.stationarrrivetest

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "ServiceResult", strict = false)
data class ServiceResult(
    @field:Element(name = "msgHeader", required = false)
    var msgHeader: MsgHeader? = null,

    @field:Element(name = "msgBody", required = false)
    var msgBody: MsgBody? = null
)