package com.example.test_task_compose_2.data.retrofit

import com.example.test_task_compose_2.data.retrofit.model.ListsUserApi
import com.example.test_task_compose_2.data.retrofit.model.UserApi
import com.example.test_task_compose_2.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GitUserService {
    @GET("users")
    suspend fun getUsers(
        @Header("Authorization") token: String = Constants.token,
        @Query("since") since: Int = 1,
        @Query("per_page") perPage: Int = 10
    ): Response<List<ListsUserApi>>

    @GET("users/{user_name}")
    suspend fun getUser(
        @Header("Authorization") token: String = Constants.token,
        @Path("user_name") userName: String
    ): Response<UserApi>
}