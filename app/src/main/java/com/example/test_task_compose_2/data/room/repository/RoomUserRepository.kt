package com.example.test_task_compose_2.data.room.repository

import com.example.test_task_compose_2.data.room.dao.UserDao
import com.example.test_task_compose_2.domain.model_db.UserDb
import javax.inject.Inject

class RoomUserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun getAllUsers() = userDao.getAll()
    fun getAllUsersFlow() = userDao.getAllFlow()
    suspend fun deleteUser(user: UserDb) = userDao.delete(user)
    suspend fun insertUser(user: UserDb) = userDao.insert(user)
}