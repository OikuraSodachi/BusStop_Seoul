package com.todokanai.busstop_seoul.dataclass

import androidx.compose.runtime.Stable

@Stable
data class StationArriveInfo(
    val id:Long,
    val lineNumber:String,
    val estTime : String,  // estimated time
)