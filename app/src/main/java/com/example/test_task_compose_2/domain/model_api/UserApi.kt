package com.example.test_task_compose_2.domain.model_api

import com.example.test_task_compose_2.domain.model_ui.UserUi
import com.google.gson.annotations.SerializedName

data class UserApi(
    val login: String? = null,
    val id: Int? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("node_id")
    val nodeId: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null,
    val name: String? = null,
    val location: String? = null,
    val email: String? = null,
    @SerializedName("twitter_username")
    val twitterUsername: String? = null,
    @SerializedName("public_repos")
    val publicRepos: Int? = null,
    @SerializedName("public_gists")
    val publicGists: Int? = null,
    val followers: Int? = null,
    val following: Int? = null,
    @SerializedName("created_at")
    val createdAt: String? = null
)

fun UserApi.apiToUi() = UserUi(
    login = this.login ?: "",
    id = this.id ?: 0,
    avatarUrl = this.avatarUrl ?: "",
    nodeId = this.nodeId ?: "",
    htmlUrl = this.htmlUrl ?: "",
    name = this.name ?: "",
    location = this.location ?: "",
    email = this.email ?: "",
    twitterUsername = this.twitterUsername ?: "",
    publicRepos = this.publicRepos ?: 0,
    publicGists = this.publicGists ?: 0,
    followers = this.followers ?: 0,
    following = this.following ?: 0,
    createdAt = this.createdAt ?: ""
)