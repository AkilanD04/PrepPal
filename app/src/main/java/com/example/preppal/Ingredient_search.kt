package com.example.preppal

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Ingredient_search : AppCompatActivity() {
    private lateinit var out: TextView
    private lateinit var add_db: Button
    private var arr = emptyArray<String?>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredient_search)
        supportActionBar?.hide()

        val ingredient_in = findViewById<EditText>(R.id.ingredient_in)
        val retrieve = findViewById<Button>(R.id.retrieve)
        add_db = findViewById(R.id.add_db)
        add_db.visibility = View.INVISIBLE
        out = findViewById(R.id.meals_out)
        out.text = " "

        add_db.setOnClickListener {
//            try {
//                mealDao.insertUsers(meal)
//                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
//            } catch (e: Exception) {
//                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
//            }
            if (arr.isNotEmpty()) {
                db_add(this)
            } else {
                Toast.makeText(this, "There is nothing to enter", Toast.LENGTH_SHORT).show()
            }
        }


        retrieve.setOnClickListener {
            if (ingredient_in.text.isEmpty()) {
                Toast.makeText(this, "enter something ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "The word is ${ingredient_in.text}", Toast.LENGTH_SHORT).show()
                out.text = "Fetching meals from API..."
                fetch_details(ingredient_in, this)
            }
        }
    }

    private fun fetch_details(ingredient_in: EditText, context: Context) {
        val url = "https://themealdb.com/api/json/v1/1/filter.php?i=${ingredient_in.text}"
        Log.d("activity", url)
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        runOnUiThread {
            out.text = " "
        }

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle the error
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonStr = response.body?.string()
                val jsonObj = JSONObject(jsonStr.toString())
                val mealsArray = jsonObj.getJSONArray("meals")

                for (i in 0 until mealsArray.length()) {
                    val mealObj = mealsArray.getJSONObject(i)

                    // Extract the values you need from the meal object and store them in variables
                    val mealId = mealObj.getString("idMeal")
                    get_meal_with_id(mealId, out, context)
                }
            }
        })
    }

    private fun get_meal_with_id(mealId: String, out: TextView, context: Context) {
        val url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=${mealId}"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle the error
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonStr = response.body?.string()
                val jsonObj = JSONObject(jsonStr.toString())
                val mealsArray = jsonObj.getJSONArray("meals")
                for (i in 0 until mealsArray.length()) {
                    val mealObj = mealsArray.getJSONObject(i)

                    // Extract the values you need from the meal object and store them in variables
                    val mealid = mealObj.getString("idMeal")
                    val mealName = mealObj.getString("strMeal")
                    val drinkAlternate = mealObj.getString("strDrinkAlternate")
                    val category = mealObj.getString("strCategory")
                    val area = mealObj.getString("strArea")
                    val instructions =
                        mealObj.getString("strInstructions")
                    val mealThumb = mealObj.getString("strMealThumb")
                    val tags = mealObj.getString("strTags")
                    val youtubeLink = mealObj.getString("strYoutube")
                    val ingredient1 =
                        if (mealObj.has("strIngredient1")) {
                            val text = mealObj.getString("strIngredient1")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient2 =
                        if (mealObj.has("strIngredient2")) {
                            val text = mealObj.getString("strIngredient2")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient3 =
                        if (mealObj.has("strIngredient3")) {
                            val text = mealObj.getString("strIngredient3")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient4 =
                        if (mealObj.has("strIngredient4")) {
                            val text = mealObj.getString("strIngredient4")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient5 =
                        if (mealObj.has("strIngredient5")) {
                            val text = mealObj.getString("strIngredient5")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient6 =
                        if (mealObj.has("strIngredient6")) {
                            val text = mealObj.getString("strIngredient6")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient7 =
                        if (mealObj.has("strIngredient7")) {
                            val text = mealObj.getString("strIngredient7")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient8 =
                        if (mealObj.has("strIngredient8")) {
                            val text = mealObj.getString("strIngredient8")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient9 =
                        if (mealObj.has("strIngredient9")) {
                            val text = mealObj.getString("strIngredient9")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient10 = if (mealObj.has("strIngredient10")) {
                        val text = mealObj.getString("strIngredient10")
                        if (text.isEmpty() || text.trim().isEmpty()){
                            "null"
                        } else {
                            text
                        }
                    } else {
                        "null"
                    }
                    val ingredient11 =
                        if (mealObj.has("strIngredient11")) {
                            val text = mealObj.getString("strIngredient11")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient12 =
                        if (mealObj.has("strIngredient12")) {
                            val text = mealObj.getString("strIngredient12")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient13 =
                        if (mealObj.has("strIngredient13")) {
                            val text = mealObj.getString("strIngredient13")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient14 =
                        if (mealObj.has("strIngredient14")) {
                            val text = mealObj.getString("strIngredient14")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient15 =
                        if (mealObj.has("strIngredient15")) {
                            val text = mealObj.getString("strIngredient15")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient16 =
                        if (mealObj.has("strIngredient16")) {
                            val text = mealObj.getString("strIngredient16")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient17 =
                        if (mealObj.has("strIngredient17")) {
                            val text = mealObj.getString("strIngredient17")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient18 =
                        if (mealObj.has("strIngredient18")) {
                            val text = mealObj.getString("strIngredient18")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient19 =
                        if (mealObj.has("strIngredient19")) {
                            val text = mealObj.getString("strIngredient19")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient20 =
                        if (mealObj.has("strIngredient20")) {
                            val text = mealObj.getString("strIngredient20")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure1 =
                        if (mealObj.has("strMeasure1")) {
                            val text = mealObj.getString("strMeasure1")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure2 =
                        if (mealObj.has("strMeasure2")) {
                            val text = mealObj.getString("strMeasure2")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure3 =
                        if (mealObj.has("strMeasure3")) {
                            val text = mealObj.getString("strMeasure3")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure4 =
                        if (mealObj.has("strMeasure4")) {
                            val text = mealObj.getString("strMeasure4")
                            text.ifEmpty { "null" }
                        } else {
                            "null"
                        }
                    val measure5 =
                        if (mealObj.has("strMeasure5")) {
                            val text = mealObj.getString("strMeasure5")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure6 =
                        if (mealObj.has("strMeasure6")) {
                            val text = mealObj.getString("strMeasure6")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure7 =
                        if (mealObj.has("strMeasure7")) {
                            val text = mealObj.getString("strMeasure7")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure8 =
                        if (mealObj.has("strMeasure8")) {
                            val text = mealObj.getString("strMeasure8")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure9 =
                        if (mealObj.has("strMeasure9")) {
                            val text = mealObj.getString("strMeasure9")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure10 =
                        if (mealObj.has("strMeasure10")) {
                            val text = mealObj.getString("strMeasure10")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure11 =
                        if (mealObj.has("strMeasure11")) {
                            val text = mealObj.getString("strMeasure11")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure12 =
                        if (mealObj.has("strMeasure12")) {
                            val text = mealObj.getString("strMeasure12")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure13 =
                        if (mealObj.has("strMeasure13")) {
                            val text = mealObj.getString("strMeasure13")
                            text.ifEmpty { "null" }
                        } else {
                            "null"
                        }
                    val measure14 =
                        if (mealObj.has("strMeasure14")) {
                            val text = mealObj.getString("strMeasure14")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure15 =
                        if (mealObj.has("strMeasure15")) {
                            val text = mealObj.getString("strMeasure15")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure16 =
                        if (mealObj.has("strMeasure16")) {
                            val text = mealObj.getString("strMeasure16")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure17 =
                        if (mealObj.has("strMeasure17")) {
                            val text = mealObj.getString("strMeasure17")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure18 =
                        if (mealObj.has("strMeasure18")) {
                            val text = mealObj.getString("strMeasure18")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure19 =
                        if (mealObj.has("strMeasure19")) {
                            val text = mealObj.getString("strMeasure19")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val measure20 =
                        if (mealObj.has("strMeasure20")) {
                            val text = mealObj.getString("strMeasure20")
                            if (text.isEmpty() || text.trim().isEmpty()){
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }


                    runOnUiThread {
                        arr += mealId
                        arr += mealName
                        arr += drinkAlternate
                        arr += category
                        arr += area
                        arr += instructions
                        arr += mealThumb
                        arr += tags
                        arr += youtubeLink
                        arr += ingredient1
                        arr += ingredient2
                        arr += ingredient3
                        arr += ingredient4
                        arr += ingredient5
                        arr += ingredient6
                        arr += ingredient7
                        arr += ingredient8
                        arr += ingredient9
                        arr += ingredient10
                        arr += ingredient11
                        arr += ingredient12
                        arr += ingredient13
                        arr += ingredient14
                        arr += ingredient15
                        arr += ingredient16
                        arr += ingredient17
                        arr += ingredient18
                        arr += ingredient19
                        arr += ingredient20
                        arr += measure1
                        arr += measure2
                        arr += measure3
                        arr += measure4
                        arr += measure5
                        arr += measure6
                        arr += measure7
                        arr += measure8
                        arr += measure9
                        arr += measure10
                        arr += measure11
                        arr += measure12
                        arr += measure13
                        arr += measure14
                        arr += measure15
                        arr += measure16
                        arr += measure17
                        arr += measure18
                        arr += measure19
                        arr += measure20
                        out.append(
                            "\nmeal id: $mealId \nmeal name: $mealName \nDrinkAlternate: $drinkAlternate \nCategory: $category " +
                                    "\narea: $area \ninstructions: $instructions \nmealThumb: $mealThumb \ntags: $tags " +
                                    "\nyoutubeLink: $youtubeLink \n ingredient1: $ingredient1 \ningredient2: $ingredient2 " +
                                    "\ningredient3: $ingredient3 \ningredient4: $ingredient4 \n ingredient5: $ingredient5 " +
                                    "\ningredient6: $ingredient6 \ningredient7: $ingredient7 \ningredient8: $ingredient8 " +
                                    "\ningredient9: $ingredient9 \ningredient10: $ingredient10 \ningredient11: $ingredient11 " +
                                    "\ningredient12: $ingredient12 \ningredient13: $ingredient13 \ningredient14: $ingredient14 " +
                                    "\ningredient15: $ingredient15 \ningredient16: $ingredient16 \ningredient17: $ingredient17 " +
                                    "\ningredient18: $ingredient18 \ningredient19: $ingredient19 \ningredient20: $ingredient20 " +
                                    "\nmeasure1: $measure1 \nmeasure2: $measure2 \nmeasure3: $measure3 \nmeasure4: $measure5" +
                                    "\nmeasure5: $measure5 \nmeasure6: $measure6 \nmeasure7: $measure7 \nmeasure8: $measure8" +
                                    "\nmeasure9: $measure9 \nmeasure10: $measure10 \nmeasure11: $measure11 \nmeasure12: $measure12" +
                                    "\nmeasure13: $measure13 \nmeasure14: $measure14 \nmeasure15: $measure15 \nmeasure16: $measure16" +
                                    "\nmeasure17: $measure17 \nmeasure18: $measure18 \nmeasure19: $measure19 \nmeasure20: $measure20"
                        )
                        out.append("\n\n")
                        add_db.visibility = View.VISIBLE
                    }

                }
            }
        })
    }

    private fun db_add(context: Context) {
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "MealDatabase").build()
        val mealDao = db.mealDao()
        runBlocking {
            launch {
                val count = arr.size / 49
                Log.d("activity", "the value is: $count")
                for (i in 1..count) {
                    Log.d("activity", "Meal id: ${arr[0]} meal name: ${arr[1]}")
                    Log.d("activity", "====================================")
                    try {
                        mealDao.insertUsers(
                            Meals(
                                arr[0].toString().toInt(),
                                arr[1].toString(),
                                arr[2].toString(),
                                arr[3].toString(),
                                arr[4].toString(),
                                arr[5].toString(),
                                arr[6].toString(),
                                arr[7].toString(),
                                arr[8].toString(),
                                arr[9].toString(),
                                arr[10].toString(),
                                arr[11].toString(),
                                arr[12].toString(),
                                arr[13].toString(),
                                arr[14].toString(),
                                arr[15].toString(),
                                arr[16].toString(),
                                arr[17].toString(),
                                arr[18].toString(),
                                arr[19].toString(),
                                arr[20].toString(),
                                arr[21].toString(),
                                arr[22].toString(),
                                arr[23].toString(),
                                arr[24].toString(),
                                arr[25].toString(),
                                arr[26].toString(),
                                arr[27].toString(),
                                arr[28].toString(),
                                arr[29].toString(),
                                arr[30].toString(),
                                arr[31].toString(),
                                arr[32].toString(),
                                arr[33].toString(),
                                arr[34].toString(),
                                arr[35].toString(),
                                arr[36].toString(),
                                arr[37].toString(),
                                arr[38].toString(),
                                arr[39].toString(),
                                arr[40].toString(),
                                arr[41].toString(),
                                arr[42].toString(),
                                arr[43].toString(),
                                arr[44].toString(),
                                arr[45].toString(),
                                arr[46].toString(),
                                arr[47].toString(),
                                arr[48].toString(),
                            )
                        )
                        Log.d("activity", "added")
                        Toast.makeText(context, "Added ${arr[1]} to db", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Log.d("activity", "error")
                        Toast.makeText(context, "error adding ${arr[1]} to db", Toast.LENGTH_SHORT)
                            .show()
                    }
                    arr = arr.drop(49).toTypedArray()
                }
            }
        }
    }
}