package com.gyub.data.di

import com.gyub.data.AppDatabase
import com.gyub.data.dao.MissionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dao Module
 *
 * @author   Gyub
 * @created  2024/10/15
 */
@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesMissionDao(
        database: AppDatabase,
    ): MissionDao = database.missionDao()
}
