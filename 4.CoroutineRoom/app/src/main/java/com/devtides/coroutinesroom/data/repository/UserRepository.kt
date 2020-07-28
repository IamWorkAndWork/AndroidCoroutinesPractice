package com.devtides.coroutinesroom.data.repository

import androidx.lifecycle.LiveData
import com.devtides.coroutinesroom.data.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDatabase: UserDatabase?) {

    suspend fun insertUser(user: User): Long? {
        return userDatabase?.userDao()?.insertUser(user)
    }

    suspend fun inserAllUser(userList: List<User>) {
        userDatabase?.userDao()?.inserAllUser(userList)
    }

    suspend fun getUser(username: String): User? {
        return userDatabase?.userDao()?.getUser(username)
    }

    suspend fun deleteUser(id: Long): Int? {
        return userDatabase?.userDao()?.deleteUser(id)
    }

    suspend fun getAllUserCoroutine(): List<User>? {
        return userDatabase?.userDao()?.getAllUserCoroutine()
    }

    fun getAllUserFlow(): Flow<List<User>>? {
        return userDatabase?.userDao()?.getAllUserFlow()
    }

    fun getAllUserLiveData(): LiveData<List<User>>? {
        return userDatabase?.userDao()?.getAllUserLiveData()
    }

    suspend fun updateUser(user: User) {
        userDatabase?.userDao()?.updateUser(user)
    }

    suspend fun clearUser() {
        userDatabase?.userDao()?.clearUser()
    }

}