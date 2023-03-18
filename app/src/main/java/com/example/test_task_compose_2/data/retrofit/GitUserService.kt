package com.example.test_task_compose_2.data.retrofit

import com.example.test_task_compose_2.domain.model_api.ListsUserApi
import com.example.test_task_compose_2.domain.model_api.UserApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitUserService {
    @GET("users")
    suspend fun getUsers(
        @Query("since") since: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Response<List<ListsUserApi>>

    @GET("users/{user_name}")
    suspend fun getUser(
        @Path("user_name") userName: String
    ): Response<UserApi>
}