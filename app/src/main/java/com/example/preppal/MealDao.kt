package com.example.preppal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {
    @Query("SELECT * FROM Meals")
    suspend fun getAll(): List<Meals>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg meals: Meals)
}
