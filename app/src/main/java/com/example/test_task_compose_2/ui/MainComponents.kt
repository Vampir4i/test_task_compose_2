@file:OptIn(ExperimentalAnimationApi::class)

package com.example.test_task_compose_2.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.test_task_compose_2.ui.screen.home_screen.HomeScreen
import com.example.test_task_compose_2.ui.screen.user_screen.UserScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import java.net.URLEncoder

@Composable
fun NavigationComponent() {
    val navController = rememberAnimatedNavController()
    val toBackScreen = { navController.popBackStack() }
    val toProfileScreen =
        { login: String -> navController.navigate("${Screen.PROFILE.name}/$login") }

    val configuration = LocalConfiguration.current

    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.HOME.name,
    ) {
        composable(
            route = Screen.HOME.name,
            enterTransition = { enterTransaction() },
            exitTransition = { exitTransaction() }
        ) {
            HomeScreen(
                configuration = configuration,
                toProfileScreen = toProfileScreen
            )
        }
        composable(
            route = "${Screen.PROFILE.name}/{login}",
            enterTransition = { enterTransaction() },
            exitTransition = { exitTransaction() },
            arguments = listOf(navArgument("login") { type = NavType.StringType })
        ) {
            val login = it.arguments?.getString("login") ?: ""
            UserScreen(
                configuration = configuration,
                login = login,
                toBackScreen = { toBackScreen() },
                openWeb = { url ->
                    val encodedUrl = URLEncoder.encode(url, "UTF-8")
                    navController.navigate("${Screen.WEB_VIEW.name}/$encodedUrl")
                }
            )
        }
        composable(
            route = "${Screen.WEB_VIEW.name}/{url}",
            enterTransition = { enterTransaction() },
            exitTransition = { exitTransaction() },
            arguments = listOf(navArgument("url") {type = NavType.StringType})
        ) {
            val url = it.arguments?.getString("url") ?: ""
            val webViewState = rememberWebViewState(url = url)
            WebView(state = webViewState)
        }
    }
}

fun AnimatedContentScope<NavBackStackEntry>.enterTransaction(): EnterTransition? {
    return when (initialState.destination.route) {
        Screen.HOME.name -> {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }
        "${Screen.WEB_VIEW.name}/{url}" -> {
            slideIntoContainer(
                towards = AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }
        else -> null
    }
}

fun AnimatedContentScope<NavBackStackEntry>.exitTransaction(): ExitTransition? {
    return when (targetState.destination.route) {
        Screen.HOME.name -> {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }
        "${Screen.WEB_VIEW.name}/{url}" -> {
            slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }
        else -> null
    }
}

enum class Screen {
    HOME,
    PROFILE,
    WEB_VIEW
}