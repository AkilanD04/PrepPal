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

    @Query("SELECT * FROM Meals WHERE mealName LIKE '%' || :query || '%' OR ingredient1 LIKE '%' || :query || '%' OR ingredient2 LIKE '%' || :query || '%' OR ingredient3 LIKE '%' || :query || '%'" +
            " OR ingredient4 LIKE '%' || :query || '%'  OR ingredient5 LIKE '%' || :query || '%'  OR ingredient6 LIKE '%' || :query || '%'" +
            " OR ingredient7 LIKE '%' || :query || '%' OR ingredient8 LIKE '%' || :query || '%' OR ingredient9 LIKE '%' || :query || '%'" +
            " OR ingredient10 LIKE '%' || :query || '%' OR ingredient11 LIKE '%' || :query || '%' OR ingredient12 LIKE '%' || :query || '%'" +
            " OR ingredient13 LIKE '%' || :query || '%' OR ingredient14 LIKE '%' || :query || '%' OR ingredient15 LIKE '%' || :query || '%'" +
            " OR ingredient16 LIKE '%' || :query || '%' OR ingredient17 LIKE '%' || :query || '%' OR ingredient18 LIKE '%' || :query || '%'" +
            " OR ingredient19 LIKE '%' || :query || '%' OR ingredient20 LIKE '%' || :query || '%'")
    suspend fun id_return(query: String): List<Meals>
}
