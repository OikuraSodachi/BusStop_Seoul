package com.todokanai.busstop_seoul.dataclass

import androidx.compose.runtime.Stable

@Stable
data class LineInfo(
    val stNm:String,            // 정류소명
    val busInfo : List<String>  // 정류소에 위치한 버스 목록
)
