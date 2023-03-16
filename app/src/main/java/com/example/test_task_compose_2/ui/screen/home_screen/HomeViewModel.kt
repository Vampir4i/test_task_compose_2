package com.example.test_task_compose_2.ui.screen.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.test_task_compose_2.domain.model_ui.ListsUserUi
import com.example.test_task_compose_2.domain.use_case.GetUsersFromDbUseCase
import com.example.test_task_compose_2.domain.use_case.GetUsersPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersPagerUseCase: GetUsersPagerUseCase,
    private val getUsersFromDbUseCase: GetUsersFromDbUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    val getUsersPager: Flow<PagingData<ListsUserUi>> = getUsersPagerUseCase()
        .cachedIn(viewModelScope)
    val usersDbFlow: Flow<List<ListsUserUi>> = getUsersFromDbUseCase()

    fun updateLoadingState(state: LoadingState) {
        _uiState.update {
            it.copy(loadingState = state)
        }
    }

}

data class HomeScreenUiState(
    val loadingState: LoadingState = LoadingState.Init
)

sealed class LoadingState {
    object Init : LoadingState()
    object Loading : LoadingState()
    class Error(val message: String) : LoadingState()
}