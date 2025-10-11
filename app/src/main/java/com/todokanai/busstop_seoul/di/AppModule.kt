package com.todokanai.busstop_seoul.di

import android.content.Context
import com.todokanai.data.repository.SettingsRepositoryImpl
import com.todokanai.domain.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideSettingsRepository(@ApplicationContext appContext: Context): SettingsRepository {
        return SettingsRepositoryImpl(appContext)
    }
}