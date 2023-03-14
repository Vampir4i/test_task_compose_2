package com.example.test_task_compose_2.data.retrofit.repository

import com.example.test_task_compose_2.data.retrofit.GitUserService
import com.example.test_task_compose_2.domain.model_api.ErrorResponse
import com.example.test_task_compose_2.domain.model_api.apiToUi
import com.example.test_task_compose_2.domain.model_ui.ListsUserUi
import com.example.test_task_compose_2.domain.model_ui.UserUi
import javax.inject.Inject

class GitUserRepository @Inject constructor(
    private val gitUserService: GitUserService
) {
    suspend fun getUsers(
        since: Int = 1,
        perPage: Int = 10
    ): List<ListsUserUi> {
        val response = gitUserService.getUsers(
            since = since,
            perPage = perPage
        )
        return if (response.isSuccessful)
            response.body()?.map { it.apiToUi() } ?: listOf()
        else throw ErrorResponse(
            code = response.raw().code,
            msg = response.raw().message
        )
    }

    suspend fun getUser(
        userName: String
    ): UserUi? {
        val response = gitUserService.getUser(
            userName = userName
        )
        return if (response.isSuccessful)
            response.body()?.apiToUi()
        else throw ErrorResponse(
            code = response.code(),
            msg = response.message()
        )
    }

}