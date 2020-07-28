package com.devtides.coroutinesroom.data.model

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserAllUser(userList: List<User>)

    @Query("delete from user where id = :id")
    suspend fun deleteUser(id: Long): Int

    @Query("select * from user where username = :username")
    suspend fun getUser(username: String): User

    @Query("select * from user order by id asc")
    suspend fun getAllUserCoroutine(): List<User>

    @Query("select * from user order by id asc")
    fun getAllUserFlow(): Flow<List<User>>

    @Query("select * from user order by id asc")
    fun getAllUserLiveData(): LiveData<List<User>>

    @Update()
    suspend fun updateUser(user: User)

    @Query("delete from user")
    suspend fun clearUser()

}