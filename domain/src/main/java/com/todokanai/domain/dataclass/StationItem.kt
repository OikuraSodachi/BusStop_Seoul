package com.todokanai.domain.dataclass

data class StationItem(
    val stId: Long,          // 정류소 ID

    val stNm: String,          // 정류소 명

    val arsId: Long,         // 정류소 고유 번호 (5자리)

    val tmX: Double,           // 경도 (Longitude)

    val tmY: Double,           // 위도 (Latitude)

    val posX: String? = null,          // 좌표 X (GRS80)

    val posY: String? = null,           // 좌표 Y (GRS80)

    val stationTp: String? = null  // 정류소 타입	(0:공용, 1:일반형 시내/농어촌버스, 2:좌석형 시내/농어촌버스, 3:직행좌석형 시내/농어촌버스, 4:일반형 시외버스, 5:좌석형 시외버스, 6:고속형 시외버스, 7:마을버스)
)