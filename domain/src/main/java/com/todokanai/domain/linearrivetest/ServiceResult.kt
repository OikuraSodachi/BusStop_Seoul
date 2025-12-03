package com.todokanai.domain.linearrivetest

data class ServiceResult(
    val comMsgHeader: Any,
    val msgBody: MsgBody,
    val msgHeader: MsgHeader
)