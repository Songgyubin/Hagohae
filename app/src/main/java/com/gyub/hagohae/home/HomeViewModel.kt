package com.gyub.hagohae.home

import androidx.lifecycle.ViewModel
import com.gyub.domain.usecase.GetMissionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * 홈 뷰모델
 *
 * @author   Gyub
 * @created  2024/09/24
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMissionsUseCase: GetMissionsUseCase,
) : ViewModel() {

}
