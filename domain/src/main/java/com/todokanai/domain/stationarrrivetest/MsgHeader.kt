package com.todokanai.domain.stationarrrivetest

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "msgHeader", strict = false)
data class MsgHeader(
    @field:Element(name = "headerCd", required = false)
    var headerCd: String? = null,

    @field:Element(name = "headerMsg", required = false)
    var headerMsg: String? = null,

    @field:Element(name = "itemCount", required = false)
    var itemCount: Int? = null
)
