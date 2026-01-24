package com.todokanai.data.retrofit.header

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "msgHeader", strict = false)
data class MsgHeader(
    @field:Element(name = "headerCd", required = false)
    val headerCd: String? = null,

    @field:Element(name = "headerMsg", required = false)
    val headerMsg: String? = null,

    @field:Element(name = "itemCount", required = false)
    val itemCount: Int? = null
)