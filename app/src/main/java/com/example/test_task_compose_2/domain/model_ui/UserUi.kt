package com.example.test_task_compose_2.domain.model_ui

import com.example.test_task_compose_2.domain.model_db.UserDb

data class UserUi(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val nodeId: String,
    val htmlUrl: String,
    val name: String,
    val location: String,
    val email: String,
    val twitterUsername: String,
    val publicRepos: Int,
    val publicGists: Int,
    val followers: Int,
    val following: Int,
    val createdAt: String
)

fun UserUi.uiToDb() = UserDb(
    id = this.id,
    login = this.login,
    avatarUrl = this.avatarUrl,
    nodeId = this.nodeId
)
