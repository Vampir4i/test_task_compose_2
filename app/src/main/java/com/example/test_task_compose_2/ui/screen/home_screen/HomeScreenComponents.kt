@file:OptIn(ExperimentalMaterialApi::class)

package com.example.test_task_compose_2.ui.screen.home_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import coil.compose.AsyncImage
import com.example.test_task_compose_2.R
import com.example.test_task_compose_2.domain.model_api.ErrorResponse
import com.example.test_task_compose_2.util.handleLoadUsers

@Composable
fun GitUserListItem(
    onItemClick: () -> Unit,
    avatarUrl: String,
    login: String,
    isBookmarked: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(8.dp),
        elevation = 4.dp,
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = avatarUrl,
                contentDescription = "users_avatar",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.git_logo_black),
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp)
                    .clip(
                        RoundedCornerShape(50)
                    )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = login,
                style = MaterialTheme.typography.h2
            )
            Spacer(modifier = Modifier.weight(1f))
            if (isBookmarked)
                Icon(
                    painter = painterResource(
                        id = R.drawable.bookmark_enable
                    ),
                    modifier = Modifier.padding(end = 8.dp),
                    contentDescription = "item_bookmarked"
                )
        }
    }
}

@Composable
fun HomeScreenToolbar(
    label: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.primary
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colors.surface.copy(
                    alpha = 0.5f
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.users_loading),
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )
        CircularProgressIndicator()
    }
}

@Composable
fun LoadFailedScreen(message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colors.surface.copy(
                    alpha = 0.5f
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(
                id = R.string.users_loading_failed,
                message
            ),
            style = MaterialTheme.typography.h2,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
    }
}

fun loadingRefreshState(
    state: LoadState,
    updateLoadingState: (LoadingState) -> Unit,
) {
    when (state) {
        is LoadState.Error -> {
            updateLoadingState(
                LoadingState.Error(
                    handleLoadUsers((state.error as ErrorResponse).code)
                )
            )
        }
        is LoadState.Loading -> {
            updateLoadingState(LoadingState.Loading)
        }

        else -> {
            updateLoadingState(LoadingState.Init)
        }
    }
}

fun LazyListScope.loadingAppendState(state: LoadState) {
    when (state) {
        is LoadState.Error -> item {
            Text(
                text = stringResource(
                    id = R.string.loading_failed,
                    handleLoadUsers((state.error as ErrorResponse).code)
                ),
                style = MaterialTheme.typography.h1,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
        is LoadState.Loading -> item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colors.surface
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.users_loading),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                )
                CircularProgressIndicator()
            }
        }
        else -> {}
    }
}

fun LazyGridScope.loadingAppendState(state: LoadState) {
    when (state) {
        is LoadState.Error -> item {
            Text(
                text = stringResource(
                    id = R.string.loading_failed,
                    handleLoadUsers((state.error as ErrorResponse).code)
                ),
                style = MaterialTheme.typography.h1,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
        is LoadState.Loading -> item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colors.surface
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.users_loading),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(8.dp)
                )
                CircularProgressIndicator()
            }
        }
        else -> {}
    }
}