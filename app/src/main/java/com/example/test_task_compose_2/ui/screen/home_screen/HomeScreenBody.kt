@file:OptIn(ExperimentalMaterialApi::class)

package com.example.test_task_compose_2.ui.screen.home_screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.test_task_compose_2.domain.model_ui.ListsUserUi
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreenBody(
    configuration: Configuration,
    usersDbFlow: Flow<List<ListsUserUi>>,
    usersApiFlow: Flow<PagingData<ListsUserUi>>,
    toProfileScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Log.d("myLog", "HomeScreenBody")
    val users = usersApiFlow.collectAsLazyPagingItems()
    val refreshing = remember { mutableStateOf(false) }
    val pullRefreshState =
        rememberPullRefreshState(refreshing = refreshing.value, onRefresh = { users.refresh() })
    Box(
        modifier = modifier.pullRefresh(pullRefreshState)
    ) {
        when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                GitUsersGrid(users = users, toProfileScreen = toProfileScreen)
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                GitUserList(users = users, toProfileScreen = toProfileScreen)
            }
            else -> {}
        }
        PullRefreshIndicator(
            refreshing.value,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

@Composable
fun GitUserList(
    users: LazyPagingItems<ListsUserUi>,
    toProfileScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = users,
            key = { it.nodeId }
        ) { user ->
            GitUserListItem(
                onItemClick = { toProfileScreen(user?.login ?: "") },
                avatarUrl = user?.avatarUrl ?: "",
                login = user?.login ?: ""
            )
        }

        loadingRefreshState(state = users.loadState.refresh)
        loadingAppendState(state = users.loadState.append)
    }
}

@Composable
fun GitUsersGrid(
    users: LazyPagingItems<ListsUserUi>,
    toProfileScreen: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(users.itemCount) { index ->
            GitUserListItem(
                onItemClick = { toProfileScreen(users[index]?.login ?: "") },
                avatarUrl = users[index]?.avatarUrl ?: "",
                login = users[index]?.login ?: ""
            )
        }

        loadingRefreshState(state = users.loadState.refresh)
        loadingAppendState(state = users.loadState.append)
    }
}