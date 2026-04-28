package com.example.habittracker.model

data class Habit(
    var name: String,
    var description: String,
    var goal: Int,
    var progress: Int,
    var icon: Int,
    var unit: String
)