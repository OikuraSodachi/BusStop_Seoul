package com.todokanai.data.repository

import com.todokanai.domain.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl : SettingsRepository {

    override fun smallMapEnabled(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun zoomControlsEnabled(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun mapToolbarEnabled(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun compassEnabled(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun myLocationButtonEnabled(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun indoorLevelPickerEnabled(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun rotationGesturesEnabled(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun scrollGesturesEnabled(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun tiltGesturesEnabled(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun zoomGesturesEnabled(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}