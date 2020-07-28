package com.devtides.coroutinesroom.domain.usecases

import com.devtides.coroutinesroom.data.model.User
import com.devtides.coroutinesroom.data.repository.UserRepository

class UpdateUserUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(user: User) {
        userRepository.updateUser(user)
    }
}