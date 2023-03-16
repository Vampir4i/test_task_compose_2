package com.example.test_task_compose_2.ui.screen.user_screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.test_task_compose_2.R
import com.example.test_task_compose_2.domain.model_ui.LoadingState
import com.example.test_task_compose_2.domain.model_ui.UserUi
import com.example.test_task_compose_2.ui.LoadFailedScreen
import com.example.test_task_compose_2.ui.LoadingScreen
import kotlinx.coroutines.flow.StateFlow

@Composable
fun UserScreenBody(
    configuration: Configuration,
    uiStateFlow: StateFlow<UserScreenUiState>,
    addUserToDb: () -> Unit,
    deleteUserFromDb: () -> Unit,
    openWeb: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = uiStateFlow.collectAsState()
    when (val state = uiState.value.loadingState) {
        is LoadingState.Error -> {
            LoadFailedScreen(
                stringId = R.string.user_loading_failed,
                message = state.message
            )
        }
        LoadingState.Init -> {
            uiState.value.user?.let {
                UserInfoScreen(
                    configuration = configuration,
                    userUi = it,
                    isBookmarked = uiState.value.isBookmarked,
                    addUserToDb = addUserToDb,
                    deleteUserFromDb = deleteUserFromDb,
                    openWeb = openWeb,
                    modifier = modifier
                )
            }
        }
        LoadingState.Loading -> {
            LoadingScreen(stringId = R.string.user_loading)
        }
    }
}

@Composable
fun UserInfoScreen(
    configuration: Configuration,
    userUi: UserUi,
    isBookmarked: Boolean,
    addUserToDb: () -> Unit,
    deleteUserFromDb: () -> Unit,
    openWeb: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                UserInfoWithOrientation(
                    userUi = userUi,
                    isBookmarked = isBookmarked,
                    addUserToDb = addUserToDb,
                    deleteUserFromDb = deleteUserFromDb,
                    openWeb = openWeb,
                    shape = MaterialTheme.shapes.medium.copy(
                        topStart = CornerSize(0.dp),
                        topEnd = CornerSize(0.dp)
                    ),
                    modifierAvatar = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                    modifierInfo = Modifier
                        .weight(3f)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
            ) {
                UserInfoWithOrientation(
                    userUi = userUi,
                    isBookmarked = isBookmarked,
                    addUserToDb = addUserToDb,
                    deleteUserFromDb = deleteUserFromDb,
                    openWeb = openWeb,
                    shape = MaterialTheme.shapes.medium.copy(
                        topStart = CornerSize(0.dp),
                        bottomStart = CornerSize(0.dp)
                    ),
                    modifierAvatar = Modifier
                        .weight(2f)
                        .fillMaxHeight(),
                    modifierInfo = Modifier
                        .weight(3f)
                        .padding(horizontal = 16.dp, vertical = 0.dp)
                )
            }
        }
        else -> {}
    }
}