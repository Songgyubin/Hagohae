package com.gyub.data.di

import com.gyub.data.repository.MissionRepositoryImpl
import com.gyub.domain.repository.MissionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Repository Module
 *
 * @author   Gyub
 * @created  2024/10/21
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsMissionRepository(missionRepositoryImpl: MissionRepositoryImpl): MissionRepository
}
