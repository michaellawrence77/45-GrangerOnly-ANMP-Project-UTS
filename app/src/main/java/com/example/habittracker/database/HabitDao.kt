package com.example.habittracker.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.habittracker.model.Habit

@Dao
interface HabitDao {

    @Query("SELECT * FROM habit")
    fun getAllHabit(): LiveData<List<Habit>>

    @Query("SELECT * FROM habit WHERE id = :id")
    fun getHabit(id: Int): LiveData<Habit>

    @Insert
    suspend fun insertHabit(habit: Habit)

    @Update
    suspend fun updateHabit(habit: Habit)

    @Query("DELETE FROM habit WHERE id = :id")
    suspend fun deleteHabit(id: Int)
}