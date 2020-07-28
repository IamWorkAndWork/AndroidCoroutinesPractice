package com.devtides.coroutinesroom.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var username: String,

    @ColumnInfo(name = "password_hash")
    var passwordHash: Int,

    var info: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}