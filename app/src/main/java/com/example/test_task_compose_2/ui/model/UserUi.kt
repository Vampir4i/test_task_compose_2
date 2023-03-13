package com.example.test_task_compose_2.ui.model

import com.google.gson.annotations.SerializedName

data class UserUi(
    val login: String,
    val id: Int,
    val avatarUrl: String,
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
