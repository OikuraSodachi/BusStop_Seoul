package com.todokanai.busstop_seoul.dataclass

import androidx.compose.runtime.Stable
import com.google.android.gms.maps.model.LatLng

@Stable
data class MarkerInfo(
    val id: Long,       // Primary key (?)
    val position: LatLng,
    val title: String?,
    val snippet: String?
)