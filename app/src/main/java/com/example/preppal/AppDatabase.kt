package com.example.preppal

import androidx.room.Database
import androidx.room.RoomDatabase

// Annotates the class as a Room database with a table for Meals and a version number.
// exportSchema = false means that we're not exporting the schema for this database.
@Database(entities = [Meals::class], version = 1, exportSchema = false)

// Defines an abstract class that extends RoomDatabase, which is the main access point for the database.
abstract class AppDatabase : RoomDatabase() {

    // Declares an abstract function that returns an instance of the DAO interface for Meals.
    // The function is implemented by Room at compile time.
    abstract fun mealDao(): MealDao
}