package com.example.test_task_compose_2.domain.use_case

import com.example.test_task_compose_2.data.room.repository.RoomUserRepository
import com.example.test_task_compose_2.domain.model_db.dbToUi
import com.example.test_task_compose_2.domain.model_ui.ListsUserUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUsersFromDbUseCase @Inject constructor(
    private val roomUserRepository: RoomUserRepository
) {
    operator fun invoke(): Flow<List<ListsUserUi>> {
        return roomUserRepository.getAllUsersFlow().map { usersList ->
            usersList.map { user ->
                user.dbToUi()
            }
        }
    }

    suspend fun getUsers(): List<ListsUserUi> {
        return roomUserRepository.getAllUsers().map { it.dbToUi() }
    }
}