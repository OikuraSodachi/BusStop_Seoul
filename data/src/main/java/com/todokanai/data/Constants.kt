package com.todokanai.data

object Constants {

    const val STATION_INFO_BASE_URL = "http://ws.bus.go.kr/api/rest/stationinfo/"    // 서울특별시_정류소정보조회 서비스
    const val BUS_POSITION_BASE_URL = "http://ws.bus.go.kr/api/rest/buspos/"            // 서울특별시_버스위치정보조회 서비스
    const val BUS_ARRIVE_INFO_BASE_URL = "http://ws.bus.go.kr/api/rest/arrive/"     // 서울특별시_버스도착정보조회 서비스

    // Todo: https 대신 http를 사용해야 할 가능성
    const val BUS_LINE_BASE_URL = "https://apis.data.go.kr/1613000/BusRoute/"        // 국토교통부_버스노선
    const val BUS_STOP_BASE_URL = "https://apis.data.go.kr/1613000/BusStop/"        // 국토교통부_버스정류장
    const val THROUGH_STATION_BASE_URL = "https://apis.data.go.kr/1613000/BusRoutespecificStopInformation/"  // 국토교통부_버스노선별 경유정류장
}