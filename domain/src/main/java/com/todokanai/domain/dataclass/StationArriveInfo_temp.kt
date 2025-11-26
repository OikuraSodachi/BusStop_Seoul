package com.todokanai.domain.dataclass

/** REST Api 가져오기 전까지만 임시로 사용 **/
data class StationArriveInfo_temp(
    val id:Long,
    val lineNumber:String,
    val estTime : Long,  // estimated time
)
