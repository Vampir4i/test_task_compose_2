package com.example.test_task_compose_2.domain.use_case

import com.example.test_task_compose_2.domain.model_api.ErrorResponse
import com.example.test_task_compose_2.data.retrofit.repository.GitUserRepository
import com.example.test_task_compose_2.domain.model_ui.UserUi
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val gitUserRepository: GitUserRepository
) {
    suspend operator fun invoke(userName: String): Result<UserUi> {
        return try {
            val user = gitUserRepository.getUser(
                userName = userName
            )
            if (user != null)
                Result.success(user)
            else Result.failure(Throwable("Empty user"))
        } catch (er: ErrorResponse) {
            Result.failure(er)
        }
    }
}