package com.todokanai.busstop_seoul.util

import com.todokanai.busstop_seoul.dataclass.searchresult.LineSearchResult
import com.todokanai.busstop_seoul.dataclass.searchresult.StationSearchResult
import com.todokanai.domain.dataclass.BusLineItem
import com.todokanai.domain.dataclass.StationItem

fun StationItem.toStationSearchResult(favorite:Boolean):StationSearchResult{
    return StationSearchResult(
        stId = stId,
        stNm = stNm,
        arsId = arsId,
        tmX = tmX,
        tmY = tmY,
        posX = posX,
        posY = posY,
        stationTp = stationTp,
        favorite = favorite
    )
}

fun BusLineItem.toLineSearchResult(favorite:Boolean):LineSearchResult{
    return LineSearchResult(
        busRouteId = busRouteId,
        rtNm = rtNm,
        routeAbrv = routeAbrv,
        routeType = routeType,
        stBegin = stBegin,
        stEnd = stEnd,
        term = term,
        firstBusTm = firstBusTm,
        lastBusTm = lastBusTm,
        favorite = favorite
    )
}