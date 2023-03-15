package com.example.test_task_compose_2.ui.screen.home_screen

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.test_task_compose_2.domain.model_ui.ListsUserUi
import com.example.test_task_compose_2.domain.use_case.GetUsersFromDbUseCase
import com.example.test_task_compose_2.domain.use_case.GetUsersPagerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersPagerUseCase: GetUsersPagerUseCase,
    private val getUsersFromDbUseCase: GetUsersFromDbUseCase,
) : ViewModel() {

    val getUsersPager: Flow<PagingData<ListsUserUi>> = getUsersPagerUseCase().cachedIn(viewModelScope)
    val usersDbFlow = getUsersFromDbUseCase()

}