package com.example.test_task_compose_2.ui.screen.user_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_task_compose_2.domain.model_api.ErrorResponse
import com.example.test_task_compose_2.domain.model_ui.LoadingState
import com.example.test_task_compose_2.domain.model_ui.UserUi
import com.example.test_task_compose_2.domain.use_case.*
import com.example.test_task_compose_2.util.handleLoadUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserScreenViewModel @Inject constructor(
    savedState: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase,
    private val getUserFromDbUseCase: GetUserFromDbUseCase,
    private val insertUserToDbUseCase: InsertUserToDbUseCase,
    private val deleteUserFromDbUseCase: DeleteUserFromDbUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserScreenUiState())
    val uiState: StateFlow<UserScreenUiState> = _uiState

    init {
        val login = savedState.get<String>("login")
        updateLoadingState(LoadingState.Loading)
        getUser(login)
    }

    private fun getUser(login: String?) {
        if (login == null) updateLoadingState(LoadingState.Error("Empty user"))
        else {
            viewModelScope.launch {
                val result = getUserUseCase(login)
                result.onSuccess { userUi ->
                    val userDb = getUserFromDbUseCase(userUi.id)
                    _uiState.update {
                        it.copy(
                            user = userUi,
                            isBookmarked = userDb != null,
                            loadingState = LoadingState.Init
                        )
                    }
                }
                result.onFailure { error ->
                    _uiState.update {
                        it.copy(
                            loadingState = LoadingState.Error(
                                handleLoadUser((error as ErrorResponse).code)
                            )
                        )
                    }
                }
            }
        }
    }

    fun addUserToDb() {
        viewModelScope.launch {
            _uiState.value.user?.let {
                insertUserToDbUseCase(it)
            }
            updateIsBookmarked(true)
        }
    }

    fun deleteUserFromDb() {
        viewModelScope.launch {
            _uiState.value.user?.let {
                deleteUserFromDbUseCase(it)
            }
            updateIsBookmarked(false)
        }
    }

    private fun updateIsBookmarked(isBookmarked: Boolean) {
        _uiState.update {
            it.copy(isBookmarked = isBookmarked)
        }
    }

    private fun updateLoadingState(state: LoadingState) {
        _uiState.update {
            it.copy(loadingState = state)
        }
    }
}

data class UserScreenUiState(
    val user: UserUi? = null,
    val isBookmarked: Boolean = false,
    val loadingState: LoadingState = LoadingState.Init
)