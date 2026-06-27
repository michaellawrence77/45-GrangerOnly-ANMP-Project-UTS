package com.example.habittracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "habit")
data class Habit(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "goal")
    var goal: Int,

    @ColumnInfo(name = "progress")
    var progress: Int,

    @ColumnInfo(name = "icon")
    var icon: Int,

    @ColumnInfo(name = "unit")
    var unit: String

) : Serializable