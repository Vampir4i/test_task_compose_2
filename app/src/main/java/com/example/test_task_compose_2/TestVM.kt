package com.example.test_task_compose_2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.test_task_compose_2.data.room.dao.UserDao
import com.example.test_task_compose_2.domain.model_api.ErrorResponse
import com.example.test_task_compose_2.domain.model_ui.ListsUserUi
import com.example.test_task_compose_2.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestVM @Inject constructor(
    private val getUsersPagerUseCase: GetUsersPagerUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getUsersFromDbUseCase: GetUsersFromDbUseCase,
    private val addUserToDbUseCase: InsertUserToDbUseCase,
    private val deleteUserFromDbUseCase: DeleteUserFromDbUseCase
) : ViewModel() {

    fun getUsersPager(): Flow<PagingData<ListsUserUi>> = getUsersPagerUseCase()

    val usersFlow = getUsersFromDbUseCase()

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

    fun addUserToDb(listsUserUi: ListsUserUi) {
        viewModelScope.launch {
            addUserToDbUseCase(listsUserUi)
        }
    }

    fun deleteUserFromDb(listsUserUi: ListsUserUi) {
        viewModelScope.launch {
            deleteUserFromDbUseCase(listsUserUi)
        }
    }
}