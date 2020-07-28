package com.devtides.coroutinesroom.presentation.userlist

import android.app.Application
import androidx.lifecycle.*
import com.devtides.coroutinesroom.data.model.User
import com.devtides.coroutinesroom.domain.usecases.DeleteUserUseCase
import com.devtides.coroutinesroom.domain.usecases.GetUserUseCase
import com.devtides.coroutinesroom.domain.usecases.InsertUserUserCase
import com.devtides.coroutinesroom.domain.usecases.UpdateUserUseCase
import kotlinx.coroutines.launch
import java.util.*

class UserListViewModel(
    application: Application,
    private val getUserUseCase: GetUserUseCase,
    private val insertUserUserCase: InsertUserUserCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : AndroidViewModel(application) {

    private val TAG by lazy {
        javaClass.simpleName
    }
    private val allUserCoroutine = MutableLiveData<List<User>>()

    init {
        fetchAllUserCoroutine()
    }

    fun getAllUserLiveData(): LiveData<List<User>>? {
        return getUserUseCase.getAllUsetLiveData()
    }

    fun getAllUserFlow(): LiveData<List<User>>? {
        return getUserUseCase.getAllUserFlow()?.asLiveData()
    }

    fun getAllUserCoroutine(): MutableLiveData<List<User>> {
        return allUserCoroutine
    }

    fun fetchAllUserCoroutine() {
        viewModelScope.launch {
            allUserCoroutine.value = getUserUseCase.getAllUserCoroutine()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            insertUserUserCase.invoke(user)
        }
    }

    fun addUser(userList: List<User>) {
        viewModelScope.launch {
            insertUserUserCase.invoke(userList)
        }
    }

    fun deleteUser(id: Long) {
        viewModelScope.launch {
            deleteUserUseCase.invoke(id)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            val time = Date().time
            user.username = "update${time}"
            user.info = "info$time"
            updateUserUseCase.invoke(user)
        }
    }

    fun clearUser() {
        viewModelScope.launch {
            deleteUserUseCase.clearUser()
        }
    }

    fun ramdomUser(): User {
        val time = Date().time
        val user = User(
            username = "user$time",
            info = "Info$time}",
            passwordHash = "111111".hashCode()
        )
        return user
    }

    class Factory(
        private val application: Application,
        private val getUserUseCase: GetUserUseCase,
        private val insertUserUserCase: InsertUserUserCase,
        private val deleteUserUseCase: DeleteUserUseCase,
        private val updateUserUseCase: UpdateUserUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UserListViewModel(
                application,
                getUserUseCase,
                insertUserUserCase,
                deleteUserUseCase,
                updateUserUseCase
            ) as T
        }
    }

}