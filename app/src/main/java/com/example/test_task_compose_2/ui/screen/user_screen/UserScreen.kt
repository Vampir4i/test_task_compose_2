package com.example.test_task_compose_2.ui.screen.user_screen

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.test_task_compose_2.util.VibrateUtil

@Composable
fun UserScreen(
    configuration: Configuration,
    login: String,
    toBackScreen: () -> Unit,
    openWeb: (String) -> Unit,
    userScreenViewModel: UserScreenViewModel = hiltViewModel()
) {
    BackHandler {
        VibrateUtil.vibrate(VibrateUtil.simpleClickVibrationEffect())
        toBackScreen()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            UserScreenToolbar(
                label = login,
                toBackScreen = toBackScreen
            )
        }
    ) { innerPadding ->
        UserScreenBody(
            configuration = configuration,
            uiStateFlow = userScreenViewModel.uiState,
            addUserToDb = userScreenViewModel::addUserToDb,
            deleteUserFromDb = userScreenViewModel::deleteUserFromDb,
            openWeb = openWeb,
            modifier = Modifier.padding(innerPadding)
        )
    }
}