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

}