package com.todokanai.data.di

import android.content.Context
import androidx.room.Room
import com.todokanai.data.repository.LocalDataRepository
import com.todokanai.data.repository.SettingsRepositoryImpl
import com.todokanai.data.repository.StationRepositoryImpl
import com.todokanai.data.room.MyDatabase
import com.todokanai.data.room.StationItemDao
import com.todokanai.domain.MapUseCase
import com.todokanai.domain.SettingsRepository
import com.todokanai.domain.StationRepository
import com.todokanai.domain.StationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMyDatabase(@ApplicationContext appContext: Context): MyDatabase {
        return Room.databaseBuilder(
            appContext,
            MyDatabase::class.java,
            "my_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideSettingsRepository(@ApplicationContext appContext: Context): SettingsRepository {
        return SettingsRepositoryImpl(appContext)
    }

    @Provides
    fun provideMapUseCase(settingsRepository: SettingsRepository): MapUseCase {
        return MapUseCase(settingsRepository)
    }

    @Provides
    fun provideStationRepository(): StationRepository {
        return StationRepositoryImpl()
    }

    @Provides
    fun provideStationItemDao(myDatabase: MyDatabase): StationItemDao {
        return myDatabase.stationItemDao()
    }

    @Provides
    fun provideLocalDataRepository(stationItemDao: StationItemDao): LocalDataRepository{
        return LocalDataRepository(stationItemDao)
    }

    @Provides
    fun provideStationUseCase(stationRepository: StationRepository): StationUseCase {
        return StationUseCase(stationRepository)
    }
}