package com.todokanai.busstop_seoul.dataclass

import androidx.compose.runtime.Stable
import com.google.android.gms.maps.model.LatLng

@Stable
data class MarkerInfo(
    val stationInfo: StationInfo,
    val position: LatLng,       // Todo: position 변수를 두지 말고, stationInfo 내부 값을 활용하기
    val title: String?,
    val snippet: String?
)