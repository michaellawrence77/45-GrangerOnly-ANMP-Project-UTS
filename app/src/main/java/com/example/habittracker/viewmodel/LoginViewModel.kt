package com.example.habittracker.viewmodel

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    fun login(username: String, password: String): Boolean {
        return username == "student" && password == "123"
    }
}