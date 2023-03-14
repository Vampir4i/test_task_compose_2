package com.example.test_task_compose_2.domain.model_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.test_task_compose_2.domain.model_ui.ListsUserUi

@Entity
data class UserDb(
    @PrimaryKey
    var id: Int,
    var login: String,
    var avatarUrl: String,
    var nodeId: String
)

fun UserDb.dbToUi() = ListsUserUi(
    id = this.id,
    login = this.login,
    avatarUrl = this.avatarUrl,
    nodeId = this.nodeId
)
