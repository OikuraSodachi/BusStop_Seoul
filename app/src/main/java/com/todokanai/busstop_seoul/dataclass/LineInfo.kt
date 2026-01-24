package com.todokanai.busstop_seoul.dataclass

import androidx.compose.runtime.Stable

@Stable
data class LineInfo(
    val stationInfo : StationInfo,
    val busInfo : String?
)
