package com.example.test_task_compose_2.ui.screen.user_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UserScreen(
    configuration: Configuration,
    login: String,
    toBackScreen: () -> Unit,
    openWeb: (String) -> Unit,
    userScreenViewModel: UserScreenViewModel = hiltViewModel()
) {
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