package com.todokanai.data.di

import android.content.Context
import com.todokanai.data.repository.SettingsRepositoryImpl
import com.todokanai.domain.MapUseCase
import com.todokanai.domain.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

//    @Singleton
//    @Provides
//    fun provideMyDatabase(@ApplicationContext appContext: Context): MyDatabase {
//        return Room.databaseBuilder(
//            appContext,
//            MyDatabase::class.java,
//            "my_database"
//        )
//            .fallbackToDestructiveMigration()
//            .build()
//    }
    @Provides
    fun provideSettingsRepository(@ApplicationContext appContext: Context): SettingsRepository {
        return SettingsRepositoryImpl(appContext)
    }

    @Provides
    fun provideMapUseCase(settingsRepository: SettingsRepository): MapUseCase {
        return MapUseCase(settingsRepository)
    }

}