package com.todokanai.domain.dataclass

data class BusPositionItem(

    val vehId: String? = null,          // 버스 ID

    val plainNo: String? = null,        // 차량 번호

    val busType: String? = null,        // 버스 타입 (0:일반, 1:저상, 2:굴절)

    val lastStnId: String? = null,      // 최근 정류소 ID

    val congetion: String? = null,      // 혼잡도 (3:여유, 4:보통, 5:혼잡)

    val sectOrd: String? = null,        // 구간 순서

    val gpsX: String? = null,           // 경도 (WGS84)

    val gpsY: String? = null,           // 위도 (WGS84)

    val isFullFlag: String? = null,     // 만차 여부 (0:여유, 1:만차)

    val stopFlag: String? = null        // 정류소 정차 여부 (0:운행중, 1:정차)
)