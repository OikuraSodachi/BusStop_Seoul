package com.todokanai.data.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.todokanai.data.abstracts.BaseDataStore
import com.todokanai.domain.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(appContext: Context) : BaseDataStore(appContext), SettingsRepository {

    override fun smallMapEnabled(): Flow<Boolean> {
        return SMALL_MAP_ENABLED.notNullFlow(defaultValue = false)
    }

    override fun zoomControlsEnabled(): Flow<Boolean> {
        return ZOOM_CONTROLS_ENABLED.notNullFlow(defaultValue = false)
    }

    override fun mapToolbarEnabled(): Flow<Boolean> {
        return MAP_TOOLBAR_ENABLED.notNullFlow(defaultValue = false)
    }

    override fun compassEnabled(): Flow<Boolean> {
        return COMPASS_ENABLED.notNullFlow(defaultValue = false)
    }

    override fun myLocationButtonEnabled(): Flow<Boolean> {
        return MY_LOCATION_BUTTON_ENABLED.notNullFlow(defaultValue = false)
    }

    override fun indoorLevelPickerEnabled(): Flow<Boolean> {
        return INDOOR_LEVEL_PICKER_ENABLED.notNullFlow(defaultValue = false)
    }

    override fun rotationGesturesEnabled(): Flow<Boolean> {
        return ROTATION_GESTURES_ENABLED.notNullFlow(defaultValue = false)
    }

    override fun scrollGesturesEnabled(): Flow<Boolean> {
        return SCROLL_GESTURES_ENABLED.notNullFlow(defaultValue = false)
    }

    override fun tiltGesturesEnabled(): Flow<Boolean> {
        return TILT_GESTURES_ENABLED.notNullFlow(defaultValue = false)
    }

    override fun zoomGesturesEnabled(): Flow<Boolean> {
        return ZOOM_GESTURES_ENABLED.notNullFlow(defaultValue = false)
    }

    override suspend fun saveSmallMapEnabled(value: Boolean) {
        SMALL_MAP_ENABLED.save(value)
    }

    override suspend fun saveZoomControlsEnabled(value: Boolean) {
        ZOOM_CONTROLS_ENABLED.save(value)
    }

    override suspend fun saveMapToolbarEnabled(value: Boolean) {
        MAP_TOOLBAR_ENABLED.save(value)
    }

    override suspend fun saveCompassEnabled(value: Boolean) {
        COMPASS_ENABLED.save(value)
    }

    override suspend fun saveMyLocationButtonEnabled(value: Boolean) {
        MY_LOCATION_BUTTON_ENABLED.save(value)
    }

    override suspend fun saveIndoorLevelPickerEnabled(value: Boolean) {
        INDOOR_LEVEL_PICKER_ENABLED.save(value)
    }

    override suspend fun saveRotationGesturesEnabled(value: Boolean) {
        ROTATION_GESTURES_ENABLED.save(value)
    }

    override suspend fun saveScrollGesturesEnabled(value: Boolean) {
        SCROLL_GESTURES_ENABLED.save(value)
    }

    override suspend fun saveTiltGesturesEnabled(value: Boolean) {
        TILT_GESTURES_ENABLED.save(value)
    }

    override suspend fun saveZoomGesturesEnabled(value: Boolean) {
        ZOOM_GESTURES_ENABLED.save(value)
    }

    companion object{
        private val SMALL_MAP_ENABLED = booleanPreferencesKey("small_map_enabled")
        private val ZOOM_CONTROLS_ENABLED = booleanPreferencesKey("zoom_controls_enabled")
        private val MAP_TOOLBAR_ENABLED = booleanPreferencesKey("map_toolbar_enabled")
        private val COMPASS_ENABLED = booleanPreferencesKey("compass_enabled")
        private val MY_LOCATION_BUTTON_ENABLED = booleanPreferencesKey("my_location_button_enabled")
        private val INDOOR_LEVEL_PICKER_ENABLED = booleanPreferencesKey("indoor_level_picker_enabled")
        private val ROTATION_GESTURES_ENABLED = booleanPreferencesKey("rotation_gestures_enabled")
        private val SCROLL_GESTURES_ENABLED = booleanPreferencesKey("scroll_gestures_enabled")
        private val TILT_GESTURES_ENABLED = booleanPreferencesKey("tilt_gestures_enabled")
        private val ZOOM_GESTURES_ENABLED = booleanPreferencesKey("zoom_gestures_enabled")
    }
}