package com.example.test_task_compose_2.hilt

import android.content.Context
import androidx.room.Room
import com.example.test_task_compose_2.data.room.UserDatabase
import com.example.test_task_compose_2.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideUserDatabase(
        @ApplicationContext context: Context
    ): UserDatabase = Room.databaseBuilder(
        context,
        UserDatabase::class.java,
        Constants.USER_TABLE
    ).build()

    @Provides
    fun provideUserDao(
        userDatabase: UserDatabase
    ) = userDatabase.userDao()
}