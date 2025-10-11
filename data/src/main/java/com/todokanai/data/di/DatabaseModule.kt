package com.todokanai.data.di

import dagger.Module
import dagger.hilt.InstallIn
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

}