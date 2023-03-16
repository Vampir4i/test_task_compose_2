package com.example.test_task_compose_2.domain.model_ui

sealed class LoadingState {
    object Init : LoadingState()
    object Loading : LoadingState()
    class Error(val message: String) : LoadingState()
}
