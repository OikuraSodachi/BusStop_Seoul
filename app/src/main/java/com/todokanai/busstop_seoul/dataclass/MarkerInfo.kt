package com.todokanai.busstop_seoul.dataclass

import com.google.android.gms.maps.model.LatLng

data class MarkerInfo(
    val id: Long,       // Primary key (?)
    val position: LatLng,
    val title: String?,
    val snippet: String?
)