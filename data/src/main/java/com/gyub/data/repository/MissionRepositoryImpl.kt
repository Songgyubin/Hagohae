package com.gyub.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gyub.data.dao.MissionDao
import com.gyub.data.model.toDomainModel
import com.gyub.domain.model.MissionModel
import com.gyub.domain.repository.MissionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Mission Repository 구현체
 *
 * @author   Gyub
 * @created  2024/10/15
 */
class MissionRepositoryImpl @Inject constructor(
    private val missionDao: MissionDao,
) : MissionRepository {
    override suspend fun getMissions(): Flow<PagingData<MissionModel>> = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { missionDao.getMissions() }
    ).flow.map { pagingData -> pagingData.map { it.toDomainModel() } }
}
