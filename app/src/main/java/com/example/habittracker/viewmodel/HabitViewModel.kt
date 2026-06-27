package com.example.habittracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.habittracker.database.HabitDatabase
import com.example.habittracker.model.Habit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    private val db = HabitDatabase.buildDatabase(application)
    private val habitDao = db.habitDao()

    val habitList: LiveData<List<Habit>> = habitDao.getAllHabit()

    fun insertHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitDao.insertHabit(habit)
        }
    }

    fun updateHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitDao.updateHabit(habit)
        }
    }

    fun deleteHabit(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            habitDao.deleteHabit(id)
        }
    }
}