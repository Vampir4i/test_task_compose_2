@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.example.test_task_compose_2.ui.screen.home_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.test_task_compose_2.R
import com.example.test_task_compose_2.domain.model_ui.ListsUserUi
import com.example.test_task_compose_2.domain.model_ui.LoadingState
import com.example.test_task_compose_2.ui.LoadFailedScreen
import com.example.test_task_compose_2.ui.LoadingScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HomeScreenBody(
    configuration: Configuration,
    usersDbFlow: Flow<List<ListsUserUi>>,
    usersApiFlow: Flow<PagingData<ListsUserUi>>,
    uiStateFlow: StateFlow<HomeScreenUiState>,
    toProfileScreen: (String) -> Unit,
    updateLoadingState: (LoadingState) -> Unit,
    modifier: Modifier = Modifier
) {
    val users = usersApiFlow.collectAsLazyPagingItems()
    val usersDb = usersDbFlow.collectAsState(initial = listOf())
    val refreshing = remember { mutableStateOf(false) }
    val pullRefreshState =
        rememberPullRefreshState(refreshing = refreshing.value, onRefresh = { users.refresh() })
    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        UserList(
            configuration = configuration,
            users = users,
            usersDb = usersDb,
            toProfileScreen = toProfileScreen,
            updateLoadingState = updateLoadingState
        )
        LoadingStateScreens(uiStateFlow = uiStateFlow)

        PullRefreshIndicator(
            refreshing = refreshing.value,
            state = pullRefreshState,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun LoadingStateScreens(
    uiStateFlow: StateFlow<HomeScreenUiState>
) {
    val uiState by uiStateFlow.collectAsState()
    when (val loadingState = uiState.loadingState) {
        is LoadingState.Error -> {
            LoadFailedScreen(
                stringId = R.string.users_loading_failed,
                message = loadingState.message
            )
        }
        LoadingState.Init -> {}

        LoadingState.Loading -> {
            LoadingScreen(R.string.users_loading)
        }
    }
}

@Composable
fun UserList(
    configuration: Configuration,
    users: LazyPagingItems<ListsUserUi>,
    usersDb: State<List<ListsUserUi>>,
    toProfileScreen: (String) -> Unit,
    updateLoadingState: (LoadingState) -> Unit
) {
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            GitUsersGrid(
                users = users,
                usersDb = usersDb,
                toProfileScreen = toProfileScreen,
                updateLoadingState = updateLoadingState
            )
        }
        Configuration.ORIENTATION_PORTRAIT -> {
            GitUserList(
                users = users,
                usersDb = usersDb,
                toProfileScreen = toProfileScreen,
                updateLoadingState = updateLoadingState
            )
        }
        else -> {}
    }
}

@Composable
fun GitUserList(
    users: LazyPagingItems<ListsUserUi>,
    usersDb: State<List<ListsUserUi>>,
    toProfileScreen: (String) -> Unit,
    updateLoadingState: (LoadingState) -> Unit,
    modifier: Modifier = Modifier
) {
    val usersFromDb by usersDb
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = users,
            key = { it.nodeId }
        ) { user ->
            GitUserListItem(
                onItemClick = { toProfileScreen(user?.login ?: "") },
                avatarUrl = user?.avatarUrl ?: "",
                login = user?.login ?: "",
                isBookmarked = usersFromDb.contains(user)
            )
        }

        loadingRefreshState(
            state = users.loadState.refresh,
            updateLoadingState = updateLoadingState
        )
        loadingAppendState(state = users.loadState.append)
    }
}

@Composable
fun GitUsersGrid(
    users: LazyPagingItems<ListsUserUi>,
    usersDb: State<List<ListsUserUi>>,
    toProfileScreen: (String) -> Unit,
    updateLoadingState: (LoadingState) -> Unit,
    modifier: Modifier = Modifier
) {
    val usersFromDb by usersDb
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize()
    ) {
        items(users.itemCount) { index ->
            GitUserListItem(
                onItemClick = { toProfileScreen(users[index]?.login ?: "") },
                avatarUrl = users[index]?.avatarUrl ?: "",
                login = users[index]?.login ?: "",
                isBookmarked = usersFromDb.contains(users[index])
            )
        }

        loadingRefreshState(
            state = users.loadState.refresh,
            updateLoadingState = updateLoadingState
        )
        loadingAppendState(state = users.loadState.append)
    }
}