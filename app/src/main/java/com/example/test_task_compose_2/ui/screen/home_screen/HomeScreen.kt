package com.example.test_task_compose_2.ui.screen.home_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.test_task_compose_2.R

@Composable
fun HomeScreen(
    configuration: Configuration,
    toProfileScreen: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeScreenToolbar(
                label = stringResource(
                    id = R.string.homescreen_toolbar_label
                )
            )
        }
    ) { innerPadding ->
        HomeScreenBody(
            configuration = configuration,
            usersDbFlow = homeViewModel.usersDbFlow,
            usersApiFlow = homeViewModel.getUsersPager,
            uiStateFlow = homeViewModel.uiState,
            toProfileScreen = toProfileScreen,
            updateLoadingState = homeViewModel::updateLoadingState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}