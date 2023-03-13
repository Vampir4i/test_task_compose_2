package com.example.test_task_compose_2.data.retrofit.model

class ErrorResponse(
    val code: Int,
    val msg: String
): Throwable()