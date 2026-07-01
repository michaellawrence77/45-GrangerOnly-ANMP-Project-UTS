package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.database.HabitDatabase
import com.example.habittracker.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val db = HabitDatabase.buildDatabase(application)
    private val userDao = db.userDao()

    init {
        createDefaultUser()
    }

    private fun createDefaultUser() {

        viewModelScope.launch(Dispatchers.IO) {

            if (userDao.countUser() == 0) {

                userDao.insertUser(
                    User(
                        username = "student",
                        password = "123"
                    )
                )
            }
        }
    }

    fun login(
        username: String,
        password: String,
        onResult: (Boolean) -> Unit
    ) {

        viewModelScope.launch(Dispatchers.IO) {

            val user = userDao.login(username, password)

            withContext(Dispatchers.Main) {
                onResult(user != null)
            }
        }
    }
}