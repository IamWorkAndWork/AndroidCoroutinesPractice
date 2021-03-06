package com.devtides.coroutinesroom.presentation.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.devtides.coroutinesroom.data.model.LoginState
import com.devtides.coroutinesroom.data.repository.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    val loginComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    val coroutineScope = CoroutineScope(Dispatchers.IO)
    val db by lazy {
        UserDatabase(getApplication())
            .userDao()
    }

    fun login(username: String, password: String) {

        coroutineScope.launch {
            val user = db.getUser(username)
            if (user == null) {
                withContext(Dispatchers.Main) {
                    error.value = "User Not Found"
                }
            } else {
                if (user.passwordHash == password.hashCode()) {
                    LoginState.login(user)
                    withContext(Dispatchers.Main) {
                        loginComplete.value = true
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        error.value = "Password is incorrect"
                    }
                }
            }
        }

    }
}