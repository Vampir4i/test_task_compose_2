package com.example.test_task_compose_2.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test_task_compose_2.data.room.dao.UserDao
import com.example.test_task_compose_2.domain.model_db.UserDb

@Database(
    entities = [UserDb::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}