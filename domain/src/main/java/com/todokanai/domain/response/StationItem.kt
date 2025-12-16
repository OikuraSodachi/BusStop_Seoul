package com.todokanai.domain.response

data class StationItem(
    var stId: String? = null,          // 정류소 ID

    var stNm: String? = null,          // 정류소 명

    var arsId: String? = null,         // 정류소 고유 번호 (5자리)

    var tmX: String? = null,           // 경도 (Longitude)

    var tmY: String? = null,           // 위도 (Latitude)

    var posX: String? = null,          // 좌표 X (GRS80)

    var posY: String? = null           // 좌표 Y (GRS80)
)