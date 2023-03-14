package com.example.test_task_compose_2.domain.model_api

class ErrorResponse(
    val code: Int,
    val msg: String
): Throwable()