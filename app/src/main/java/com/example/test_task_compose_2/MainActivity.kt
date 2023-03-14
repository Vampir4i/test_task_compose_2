package com.example.test_task_compose_2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.test_task_compose_2.ui.theme.Test_task_compose_2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val testVM by viewModels<TestVM>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        testVM.getUser()
        setContent {
            Test_task_compose_2Theme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
                pagingList(testVM)
//                list(testVM)
            }
        }
    }
}

@Composable
fun list(testVM: TestVM) {
    val users by testVM.usersFlow.collectAsState(listOf())
    LazyColumn {
        items(
            items = users,
            key = { it.nodeId }
        ) { user ->
            Text(
                text = "${user.login} ${user.id}",
                modifier = Modifier
                    .height(75.dp)
                    .clickable {
                        testVM.deleteUserFromDb(user)
                    }
            )
        }
    }
}

@Composable
fun pagingList(testVM: TestVM) {
    val users = testVM.getUsersPager().collectAsLazyPagingItems()

    LazyColumn {
        items(
            items = users,
            key = { it.nodeId }
        ) { user ->
            Text(
                text = "${user?.login} ${user?.id}",
                modifier = Modifier
                    .height(75.dp)
                    .clickable {
                        if (user != null) testVM.addUserToDb(user)
                    }
            )
            Divider()
        }

        when (val state = users.loadState.refresh) {
            is LoadState.Error -> item {
                Text(
                    text = "REFRESH ERROR ${state.error.message}",
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
                        text = "Refresh Loading",
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    CircularProgressIndicator(color = Color.Black)
                }
            }

            else -> {}
        }

        when (val state = users.loadState.append) {
            is LoadState.Error -> item {
                Text(
                    text = "ERROR ${state.error.message}",
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
                    Text(text = "Pagination Loading")
                    CircularProgressIndicator(color = Color.Black)
                }
            }
            else -> {}
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Test_task_compose_2Theme {
        Greeting("Android")
    }
}