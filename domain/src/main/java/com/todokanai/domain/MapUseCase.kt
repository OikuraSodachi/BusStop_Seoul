package com.todokanai.domain

import javax.inject.Inject

class MapUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    fun smallMapEnabled() = settingsRepository.smallMapEnabled()

    fun zoomControlsEnabled() = settingsRepository.zoomControlsEnabled()

    fun mapToolbarEnabled() = settingsRepository.mapToolbarEnabled()

    fun compassEnabled() = settingsRepository.compassEnabled()

    fun myLocationButtonEnabled() = settingsRepository.myLocationButtonEnabled()

    fun indoorLevelPickerEnabled() = settingsRepository.indoorLevelPickerEnabled()

    fun rotationGesturesEnabled() = settingsRepository.rotationGesturesEnabled()

    fun scrollGesturesEnabled() = settingsRepository.scrollGesturesEnabled()

    fun tiltGesturesEnabled() = settingsRepository.tiltGesturesEnabled()

    fun zoomGesturesEnabled() = settingsRepository.zoomGesturesEnabled()

    suspend fun saveSmallMapEnabled(value: Boolean) {
        settingsRepository.saveSmallMapEnabled(value)
    }

    suspend fun saveZoomControlsEnabled(value: Boolean) {
        settingsRepository.saveZoomControlsEnabled(value)
    }

    suspend fun saveMapToolbarEnabled(value: Boolean) {
        settingsRepository.saveMapToolbarEnabled(value)
    }

    suspend fun saveCompassEnabled(value: Boolean) {
        settingsRepository.saveCompassEnabled(value)
    }

    suspend fun saveMyLocationButtonEnabled(value: Boolean) {
        settingsRepository.saveMyLocationButtonEnabled(value)
    }

    suspend fun saveIndoorLevelPickerEnabled(value: Boolean) {
        settingsRepository.saveIndoorLevelPickerEnabled(value)
    }

    suspend fun saveRotationGesturesEnabled(value: Boolean) {
        settingsRepository.saveRotationGesturesEnabled(value)
    }

    suspend fun saveScrollGesturesEnabled(value: Boolean) {
        settingsRepository.saveScrollGesturesEnabled(value)
    }

    suspend fun saveTiltGesturesEnabled(value: Boolean) {
        settingsRepository.saveTiltGesturesEnabled(value)
    }

    suspend fun saveZoomGesturesEnabled(value: Boolean) {
        settingsRepository.saveZoomGesturesEnabled(value)
    }

}