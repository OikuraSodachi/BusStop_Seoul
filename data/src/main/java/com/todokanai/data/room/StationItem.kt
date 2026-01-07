package com.todokanai.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stationItem")
data class StationItem(
    @PrimaryKey             // Todo: PrimaryKey 선택이 적절한지 확인 필요
    @ColumnInfo var stId: String,          // 정류소 ID
    @ColumnInfo var stNm: String,          // 정류소 명
    @ColumnInfo var arsId: String,         // 정류소 고유 번호 (5자리)
    @ColumnInfo var tmX: Double,           // 경도 (Longitude)
    @ColumnInfo var tmY: Double,           // 위도 (Latitude)
    @ColumnInfo var posX: String? = null,          // 좌표 X (GRS80)
    @ColumnInfo var posY: String? = null           // 좌표 Y (GRS80)
)
