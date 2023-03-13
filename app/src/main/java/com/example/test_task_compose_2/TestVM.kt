package com.example.test_task_compose_2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.test_task_compose_2.data.retrofit.GitUserService
import com.example.test_task_compose_2.data.retrofit.model.ErrorResponse
import com.example.test_task_compose_2.data.retrofit.repository.GitUserRepository
import com.example.test_task_compose_2.domain.use_case.GetUserUseCase
import com.example.test_task_compose_2.domain.use_case.GetUsersPagerUseCase
import com.example.test_task_compose_2.ui.model.ListsUserUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestVM @Inject constructor(
    private val getUsersPagerUseCase: GetUsersPagerUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    fun getUsersPager(): Flow<PagingData<ListsUserUi>> = getUsersPagerUseCase()

    fun getUser() {
        viewModelScope.launch {
            val user = getUserUseCase("mojombo")
            user.onSuccess {
                Log.d("myLog", "USER ${it.login} ${it.id}")
            }
            user.onFailure {
                when (it) {
                    is ErrorResponse -> Log.d("myLog", "getUser ERROR: ${it.code} + ${it.msg}")
                    is Exception -> Log.d("myLog", "getUser ERROR: ${it.message}")
                }
            }
        }
    }
}