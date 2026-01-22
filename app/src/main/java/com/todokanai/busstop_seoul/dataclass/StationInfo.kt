package com.todokanai.busstop_seoul.dataclass

import androidx.compose.runtime.Stable

@Stable
data class StationInfo(

    var stId: String,          // 정류소 ID

    var stNm: String,          // 정류소 명

    var arsId: String,         // 정류소 고유 번호 (5자리)

    var tmX: String,           // 경도 (Longitude)

    var tmY: String,           // 위도 (Latitude)

)
