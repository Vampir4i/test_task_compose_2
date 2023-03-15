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
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import coil.compose.AsyncImage
import com.example.test_task_compose_2.R

@Composable
fun GitUserListItem(
    onItemClick: () -> Unit,
    avatarUrl: String,
    login: String,
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

fun LazyListScope.loadingRefreshState(state: LoadState) {
    when (state) {
        is LoadState.Error -> item {
            Text(
                text = "Loading failed",
                color = Color.Red
            )
        }
        is LoadState.Loading -> item {
            Column(
                modifier = Modifier
                    .fillParentMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Loading",
                    modifier = Modifier
                        .padding(8.dp)
                )
                CircularProgressIndicator(color = Color.Black)
            }
        }

        else -> {}
    }
}

fun LazyListScope.loadingAppendState(state: LoadState) {
    when (state) {
        is LoadState.Error -> item {
            Text(
                text = "Loading failed",
                color = Color.Red
            )
        }
        is LoadState.Loading -> item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Loading")
                CircularProgressIndicator(color = Color.Black)
            }
        }
        else -> {}
    }
}

fun LazyGridScope.loadingRefreshState(state: LoadState) {
    when (state) {
        is LoadState.Error -> item {
            Text(
                text = "Loading failed",
                color = Color.Red
            )
        }
        is LoadState.Loading -> item {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Loading",
                    modifier = Modifier
                        .padding(8.dp)
                )
                CircularProgressIndicator(color = Color.Black)
            }
        }

        else -> {}
    }
}

fun LazyGridScope.loadingAppendState(state: LoadState) {
    when (state) {
        is LoadState.Error -> item {
            Text(
                text = "Loading failed",
                color = Color.Red
            )
        }
        is LoadState.Loading -> item {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Loading")
                CircularProgressIndicator(color = Color.Black)
            }
        }
        else -> {}
    }
}