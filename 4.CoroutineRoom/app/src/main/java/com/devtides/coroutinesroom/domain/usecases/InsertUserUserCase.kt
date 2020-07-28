package com.devtides.coroutinesroom.domain.usecases

import com.devtides.coroutinesroom.data.model.User
import com.devtides.coroutinesroom.data.repository.UserRepository

class InsertUserUserCase(private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User): Long? {
        return userRepository.insertUser(user)
    }

    suspend fun invoke(userList: List<User>) {
        userRepository.inserAllUser(userList)
    }

}