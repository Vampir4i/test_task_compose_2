package com.example.test_task_compose_2.util

fun handleLoadUsers(code: Int): String {
    return when (code) {
        0 -> "Connection error"
        304 -> "Not modified"
        401 -> "Requires authentication"
        else -> ""
    }
}

fun handleLoadUser(code: Int): String {
    return when (code) {
        0 -> "Connection error"
        304 -> "Not modified"
        401 -> "Requires authentication"
        404 -> "Resource not found"
        else -> ""
    }
}