package com.devtides.coroutinesroom.presentation.signup

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

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val db by lazy {
        UserDatabase(getApplication())
            .userDao()
    }

    val signupComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun signup(username: String, password: String, info: String) {
        coroutineScope.launch {
            val user = db.getUser(username)
            if (user != null) {
                withContext(Dispatchers.Main) {
                    error.value = "User already exists"
                }
            } else {
                val user = User(username, password.hashCode(), info)
                val userId = db.insertUser(user)
                user.id = userId
                LoginState.login(user)
                withContext(Dispatchers.Main) {
                    signupComplete.value = true
                }
            }
        }
    }

}