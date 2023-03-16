package com.example.test_task_compose_2.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.test_task_compose_2.domain.model_db.UserDb
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM userdb")
    suspend fun getAll(): List<UserDb>

    @Query("SELECT * FROM userdb")
    fun getAllFlow(): Flow<List<UserDb>>

    @Query("SELECT * FROM userdb WHERE id=:id")
    suspend fun getUserById(id: Int): UserDb?

    @Insert
    suspend fun insert(userDb: UserDb)

    @Delete
    suspend fun delete(userDb: UserDb)
}