package com.example.test_task_compose_2.domain.use_case

import com.example.test_task_compose_2.data.room.repository.RoomUserRepository
import com.example.test_task_compose_2.domain.model_ui.ListsUserUi
import com.example.test_task_compose_2.domain.model_ui.UserUi
import com.example.test_task_compose_2.domain.model_ui.uiToDb
import javax.inject.Inject

class DeleteUserFromDbUseCase @Inject constructor(
    private val roomUserRepository: RoomUserRepository
) {
    suspend operator fun invoke(userUi: UserUi) {
        roomUserRepository.deleteUser(userUi.uiToDb())
    }

    suspend operator fun invoke(listsUserUi: ListsUserUi) {
        roomUserRepository.deleteUser(listsUserUi.uiToDb())
    }
}