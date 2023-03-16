package com.example.test_task_compose_2.domain.use_case

import com.example.test_task_compose_2.data.room.repository.RoomUserRepository
import com.example.test_task_compose_2.domain.model_db.dbToUi
import com.example.test_task_compose_2.domain.model_ui.ListsUserUi
import javax.inject.Inject

class GetUserFromDbUseCase @Inject constructor(
    private val roomUserRepository: RoomUserRepository
) {
    suspend operator fun invoke(id: Int): ListsUserUi? {
        return roomUserRepository.getUserById(id)?.dbToUi()
    }
}