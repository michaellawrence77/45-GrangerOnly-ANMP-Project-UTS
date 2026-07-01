package com.example.habittracker.util

import android.content.Context

class SessionManager(context: Context) {

    private val pref =
        context.getSharedPreferences("login_session", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_LOGIN = "is_login"
        private const val KEY_USERNAME = "username"
    }

    fun saveLogin(username: String) {
        pref.edit()
            .putBoolean(KEY_LOGIN, true)
            .putString(KEY_USERNAME, username)
            .apply()
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(KEY_LOGIN, false)
    }

    fun getUsername(): String? {
        return pref.getString(KEY_USERNAME, "")
    }

    fun logout() {
        pref.edit().clear().apply()
    }
}