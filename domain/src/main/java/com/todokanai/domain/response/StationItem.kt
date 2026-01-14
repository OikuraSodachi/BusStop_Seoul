package com.todokanai.domain.response

/** Todo: stId, stNm, arsId, tmX, tmY 값은 nullable 하지 않은 것이 바람직함 **/
data class StationItem(
    var stId: String?,          // 정류소 ID

    var stNm: String?,          // 정류소 명

    var arsId: String?,         // 정류소 고유 번호 (5자리)

    var tmX: Double?,           // 경도 (Longitude)

    var tmY: Double?,           // 위도 (Latitude)

    var posX: String? = null,          // 좌표 X (GRS80)

    var posY: String? = null,           // 좌표 Y (GRS80)

    var stationTp: Int? = null  // 정류소 타입	(0:공용, 1:일반형 시내/농어촌버스, 2:좌석형 시내/농어촌버스, 3:직행좌석형 시내/농어촌버스, 4:일반형 시외버스, 5:좌석형 시외버스, 6:고속형 시외버스, 7:마을버스)
)