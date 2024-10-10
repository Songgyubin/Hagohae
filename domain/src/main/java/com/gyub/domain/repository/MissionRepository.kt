package com.gyub.domain.repository

import com.gyub.domain.model.MissionModel

/**
 * Mission Repository
 *
 * @author   Gyub
 * @created  2024/10/10
 */
interface MissionRepository {

    suspend fun getMissions(): List<MissionModel>
}