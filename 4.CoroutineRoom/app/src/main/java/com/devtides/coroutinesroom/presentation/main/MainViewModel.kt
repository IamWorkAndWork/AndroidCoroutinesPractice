package com.devtides.coroutinesroom.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.devtides.coroutinesroom.data.model.LoginState
import com.devtides.coroutinesroom.data.model.User
import com.devtides.coroutinesroom.data.repository.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(application: Application) : AndroidViewModel(application) {

    val userDeleted = MutableLiveData<Boolean>()
    val signout = MutableLiveData<Boolean>()
    val user = MutableLiveData<User>()

    val coroutineScope = CoroutineScope(Dispatchers.IO)
    val db by lazy {
        UserDatabase(getApplication())
            .userDao()
    }

    fun fetchUser() {
        coroutineScope.launch {
            val id = LoginState.user?.username!!
            val _user = db.getUser(id)
            withContext(Dispatchers.Main) {
                user.value = _user
            }
        }

    }

    fun onSignout() {
        LoginState.logout()
        signout.value = true
    }

    fun onDeleteUser() {
        coroutineScope.launch {
            LoginState.user?.let {
                db.deleteUser(it.id)
            }
            withContext(Dispatchers.Main) {
                LoginState.logout()
                userDeleted.value = true
            }
        }
    }

}