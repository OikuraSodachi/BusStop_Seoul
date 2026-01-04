package com.todokanai.domain.response

data class StationItem(
    var stId: String,          // 정류소 ID

    var stNm: String,          // 정류소 명

    var arsId: String,         // 정류소 고유 번호 (5자리)

    var tmX: Double,           // 경도 (Longitude)

    var tmY: Double,           // 위도 (Latitude)

    var posX: String? = null,          // 좌표 X (GRS80)

    var posY: String? = null           // 좌표 Y (GRS80)
)