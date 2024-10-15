package com.gyub.domain.repository

import com.gyub.domain.model.MissionModel
import kotlinx.coroutines.flow.Flow

/**
 * Mission Repository
 *
 * @author   Gyub
 * @created  2024/10/10
 */
interface MissionRepository {

    suspend fun getMissions(): Flow<androidx.paging.PagingData<MissionModel>>
}
