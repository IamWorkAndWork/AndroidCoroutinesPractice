package com.devtides.coroutinesroom.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("select * from user where username = :username")
    suspend fun getUser(username: String): User

    @Query("delete from user where id = :id")
    suspend fun deleteUser(id: Long)


}