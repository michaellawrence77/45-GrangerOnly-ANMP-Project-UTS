package com.example.habittracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habittracker.model.Habit

@Database(
    entities = [Habit::class],
    version = 1,
    exportSchema = false
)
abstract class HabitDatabase : RoomDatabase() {

    abstract fun habitDao(): HabitDao

    companion object {

        @Volatile
        private var INSTANCE: HabitDatabase? = null

        fun buildDatabase(context: Context): HabitDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        HabitDatabase::class.java,
                        "habitdb"
                    ).build()
                }
            }

            return INSTANCE!!
        }
    }
}