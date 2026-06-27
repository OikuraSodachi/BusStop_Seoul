package com.todokanai.data.di

import android.content.Context
import android.content.res.AssetManager
import androidx.room.Room
import com.todokanai.data.repository.ArriveInfoRepositoryImpl
import com.todokanai.data.repository.BusPositionRepositoryImpl
import com.todokanai.data.repository.LocalDataRepositoryImpl
import com.todokanai.data.repository.SettingsRepositoryImpl
import com.todokanai.data.repository.StationRepositoryImpl
import com.todokanai.data.room.BusLineItemDao
import com.todokanai.data.room.HistoryItemDao
import com.todokanai.data.room.MyDatabase
import com.todokanai.data.room.StationItemDao
import com.todokanai.domain.ArriveInfoRepository
import com.todokanai.domain.BusPositionRepository
import com.todokanai.domain.LocalDataRepository
import com.todokanai.domain.SettingsRepository
import com.todokanai.domain.StationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Todo: UseCase 에 대한 Provide 선언을 생략할지 여부 결정하기
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

    @Singleton
    @Provides
    fun provideSettingsRepository(@ApplicationContext appContext: Context): SettingsRepository {
        return SettingsRepositoryImpl(appContext)
    }

//    @Provides
//    fun provideMapUseCase(settingsRepository: SettingsRepository): MapUseCase {
//        return MapUseCase(settingsRepository)
//    }

    @Singleton
    @Provides
    fun provideStationRepository(): StationRepository {
        return StationRepositoryImpl()
    }

    @Singleton
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
    fun provideHistoryItemDao(myDatabase: MyDatabase): HistoryItemDao {
        return myDatabase.historyItemDao()
    }

    @Singleton
    @Provides
    fun provideLocalDataRepository(
        stationItemDao: StationItemDao,
        busLineItemDao: BusLineItemDao,
        historyItemDao: HistoryItemDao,
        assetManager: AssetManager
    ): LocalDataRepository {
        return LocalDataRepositoryImpl(
            stationItemDao,
            busLineItemDao,
            historyItemDao,
            assetManager
        )
    }

    @Singleton
    @Provides
    fun provideArriveInfoRepository():ArriveInfoRepository{
        return ArriveInfoRepositoryImpl()
    }

//    @Provides
//    fun provideBusUseCase(
//        stationRepository: StationRepository,
//        busPositionRepository: BusPositionRepository,
//        arriveInfoRepository: ArriveInfoRepository,
//        localDataRepository: LocalDataRepository
//    ): BusUseCase{
//        return BusUseCase(
//            stationRepository,
//            busPositionRepository,
//            arriveInfoRepository,
//            localDataRepository
//        )
//    }

    @Singleton
    @Provides
    fun provideAssetManager(@ApplicationContext context: Context): AssetManager{
        return context.assets
    }

}