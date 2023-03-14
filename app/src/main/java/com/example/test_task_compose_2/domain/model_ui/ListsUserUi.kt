package com.example.test_task_compose_2.domain.model_ui

import com.example.test_task_compose_2.domain.model_db.UserDb

data class ListsUserUi(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val nodeId: String
)

fun ListsUserUi.uiToDb() = UserDb(
    id = this.id,
    login = this.login,
    avatarUrl = this.avatarUrl,
    nodeId = this.nodeId
)