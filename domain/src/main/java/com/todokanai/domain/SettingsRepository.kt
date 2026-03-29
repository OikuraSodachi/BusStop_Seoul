package com.todokanai.domain

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun smallMapEnabled(): Flow<Boolean>

    fun zoomControlsEnabled(): Flow<Boolean>

    fun mapToolbarEnabled(): Flow<Boolean>

    fun compassEnabled(): Flow<Boolean>

    fun myLocationButtonEnabled(): Flow<Boolean>

    fun indoorLevelPickerEnabled(): Flow<Boolean>

    fun rotationGesturesEnabled(): Flow<Boolean>

    fun scrollGesturesEnabled(): Flow<Boolean>

    fun tiltGesturesEnabled(): Flow<Boolean>

    fun zoomGesturesEnabled(): Flow<Boolean>

    fun lastKnownLatitude(): Double

    fun lastKnownLongitude(): Double

    fun lastKnownZoomLevel(): Float

    suspend fun saveZoomLevel(value:Float)

    suspend fun saveLastKnownLatitude(value: Double)

    suspend fun saveLastKnownLongitude(value: Double)

    suspend fun saveSmallMapEnabled(value: Boolean)

    suspend fun saveZoomControlsEnabled(value: Boolean)

    suspend fun saveMapToolbarEnabled(value: Boolean)

    suspend fun saveCompassEnabled(value: Boolean)

    suspend fun saveMyLocationButtonEnabled(value: Boolean)

    suspend fun saveIndoorLevelPickerEnabled(value: Boolean)

    suspend fun saveRotationGesturesEnabled(value: Boolean)

    suspend fun saveScrollGesturesEnabled(value: Boolean)

    suspend fun saveTiltGesturesEnabled(value: Boolean)

    suspend fun saveZoomGesturesEnabled(value: Boolean)

}