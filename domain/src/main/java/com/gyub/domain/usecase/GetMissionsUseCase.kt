package com.gyub.domain.usecase

import com.gyub.domain.model.MissionModel
import com.gyub.domain.repository.MissionRepository
import javax.inject.Inject

/**
 * 미션 리스트 가져오는 UseCase
 *
 * @author   Gyub
 * @created  2024/10/01
 */
class GetMissionsUseCase @Inject constructor(
    private val repository: MissionRepository,
) {
    suspend operator fun invoke(): List<MissionModel> {
        return repository.getMissions()
    }
}