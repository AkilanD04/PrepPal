package com.example.preppal

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Meals::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao
}