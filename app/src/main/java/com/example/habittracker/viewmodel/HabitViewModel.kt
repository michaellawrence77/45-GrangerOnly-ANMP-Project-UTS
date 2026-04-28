package com.example.habittracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.habittracker.model.Habit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HabitViewModel : ViewModel() {

    val habitList = ArrayList<Habit>()

    fun loadData(context: Context) {
        val sharedPref = context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)

        val gson = Gson()
        val json = sharedPref.getString("habit_list", null)

        val type = object : TypeToken<ArrayList<Habit>>() {}.type

        if (json != null) {
            val data: ArrayList<Habit> = gson.fromJson(json, type)
            habitList.clear()
            habitList.addAll(data)
        }
    }

    fun saveData(context: Context) {
        val sharedPref = context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)

        val editor = sharedPref.edit()
        val gson = Gson()

        val json = gson.toJson(habitList)
        editor.putString("habit_list", json)
        editor.apply()
    }

    fun addHabit(context: Context, habit: Habit) {
        habitList.add(habit)
        saveData(context)
    }
}