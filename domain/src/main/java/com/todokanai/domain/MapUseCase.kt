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

}