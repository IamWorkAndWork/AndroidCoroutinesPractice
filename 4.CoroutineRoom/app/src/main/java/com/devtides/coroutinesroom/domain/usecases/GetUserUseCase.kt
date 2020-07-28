package com.devtides.coroutinesroom.domain.usecases

import androidx.lifecycle.LiveData
import com.devtides.coroutinesroom.data.model.User
import com.devtides.coroutinesroom.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val userRepository: UserRepository) {

    suspend fun getAllUserCoroutine(): List<User>? {
        return userRepository.getAllUserCoroutine()
    }

    fun getAllUsetLiveData(): LiveData<List<User>>? {
        return userRepository.getAllUserLiveData()
    }

    fun getAllUserFlow(): Flow<List<User>>? {
        return userRepository.getAllUserFlow()
    }

    suspend fun getUser(username: String): User? {
        return userRepository.getUser(username)
    }

}