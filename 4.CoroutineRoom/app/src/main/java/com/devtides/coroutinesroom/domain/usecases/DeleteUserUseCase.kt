package com.devtides.coroutinesroom.domain.usecases

import com.devtides.coroutinesroom.data.repository.UserRepository

class DeleteUserUseCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(id: Long): Int? {
        return userRepository.deleteUser(id)
    }

    suspend fun clearUser() {
        return userRepository.clearUser()
    }

}