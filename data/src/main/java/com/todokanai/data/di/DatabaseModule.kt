package com.todokanai.data.di

import android.content.Context
import android.content.res.AssetManager
import androidx.room.Room
import com.todokanai.data.CsvManager
import com.todokanai.data.repository.ArriveInfoRepositoryImpl
import com.todokanai.data.repository.BusPositionRepositoryImpl
import com.todokanai.data.repository.LocalDataRepositoryImpl
import com.todokanai.data.repository.SettingsRepositoryImpl
import com.todokanai.data.repository.StationRepositoryImpl
import com.todokanai.data.room.BusLineItemDao
import com.todokanai.data.room.MyDatabase
import com.todokanai.data.room.StationItemDao
import com.todokanai.domain.ArriveInfoRepository
import com.todokanai.domain.BusPositionRepository
import com.todokanai.domain.BusUseCase
import com.todokanai.domain.LocalDataRepository
import com.todokanai.domain.MapUseCase
import com.todokanai.domain.SettingsRepository
import com.todokanai.domain.StationRepository
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

    @Singleton
    @Provides
    fun provideMapUseCase(settingsRepository: SettingsRepository): MapUseCase {
        return MapUseCase(settingsRepository)
    }

    @Provides
    fun provideStationRepository(): StationRepository {
        return StationRepositoryImpl()
    }

    @Provides
    fun provideBusPositionRepository(): BusPositionRepository {
        return BusPositionRepositoryImpl()
    }

    @Provides
    fun provideStationItemDao(myDatabase: MyDatabase): StationItemDao {
        return myDatabase.stationItemDao()
    }

    @Provides
    fun provideBusLineItemDao(myDatabase: MyDatabase): BusLineItemDao {
        return myDatabase.busLineItemDao()
    }

    @Provides
    fun provideLocalDataRepository(
        stationItemDao: StationItemDao,
        busLineItemDao: BusLineItemDao,
        csvManager: CsvManager,
        assetManager: AssetManager
    ): LocalDataRepository {
        return LocalDataRepositoryImpl(
            stationItemDao,
            busLineItemDao,
            csvManager,
            assetManager
        )
    }

    @Provides
    fun provideArriveInfoRepository():ArriveInfoRepository{
        return ArriveInfoRepositoryImpl()
    }

    @Provides
    fun provideBusUseCase(
        stationRepository: StationRepository,
        arriveInfoRepository: ArriveInfoRepository,
        localDataRepository: LocalDataRepository
    ): BusUseCase{
        return BusUseCase(
            stationRepository,
            arriveInfoRepository,
            localDataRepository
        )
    }

    @Singleton
    @Provides
    fun provideCsvManager(): CsvManager{
        return CsvManager()
    }

    @Provides
    fun provideAssetManager(@ApplicationContext appContext: Context): AssetManager {
        return appContext.assets
    }

}