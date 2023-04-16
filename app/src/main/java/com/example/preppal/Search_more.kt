package com.example.preppal

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class Search_more : AppCompatActivity() {
    private lateinit var out: TextView // Declaring a TextView variable 'out'

    @SuppressLint("MissingInflatedId")
    // Overriding the onCreate() function of the Activity class
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_more)
        supportActionBar?.hide()
        // Initializing 'out' with the TextView component with the id 'text_out'
        out = findViewById(R.id.text_out)
        // Setting the text of 'out' to an empty string
        out.text = " "
        val search_btn =
            findViewById<Button>(R.id.search_in) // Initializing a Button variable 'search_btn' with the component with the id 'search_in'
        val text_in =
            findViewById<EditText>(R.id.name_in) // Initializing an EditText variable 'text_in' with the component with the id 'name_in'
        search_btn.setOnClickListener {
            // Calling the 'show' function with 'text_in' and the context 'this'
            show(text_in, this)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("meals_list", out.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        out.append(savedInstanceState.getString("meals_list"))
    }

    private fun show(text_in: EditText, context: Context) {
        // Create URL with the meal name inputted by user
        val url = "https://www.themealdb.com/api/json/v1/1/search.php?s=${text_in.text}"

        // Log the URL for debugging purposes
        Log.d("activity", url)

        // Create new instance of OkHttpClient
        val client = OkHttpClient()

        // Build the HTTP request using the URL
        val request = Request.Builder().url(url).build()

        // Clear the text in the TextView on the UI thread
        runOnUiThread {
            out.text = " "
        }

        // Enqueue the HTTP request and handle the response
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Show a toast message if the HTTP request fails
                Toast.makeText(context, "Meal not found", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                // Get the JSON response body as a string
                val jsonStr = response.body?.string()

                // Create a new JSONObject from the JSON string
                val jsonObj = JSONObject(jsonStr.toString())

                // Get the array of meals from the JSON object
                val meals = jsonObj.optJSONArray("meals")

                if (meals == null) {
                    // Show a toast message if the meals array is null
                    Log.d("activity", "meals is null")
                    runOnUiThread {
                        Toast.makeText(context, "Ingredient not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Loop through the meals array and get the meal details using the meal ID
                    for (i in 0 until meals.length()) {
                        val mealObj = meals.getJSONObject(i)

                        // Extract the values you need from the meal object and store them in variables
                        val mealId = mealObj.getString("idMeal")

                        // Call the function to get the meal details with the meal ID and display them in the TextView
                        get_meal_with_id(mealId, out, context)
                    }
                }
            }

        })
    }

    private fun get_meal_with_id(mealId: String, out: TextView, context: Context) {
        // Define the URL of the API endpoint with the meal ID as a query parameter
        val url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=${mealId}"

        // Create a new instance of OkHttpClient
        val client = OkHttpClient()

        // Create a new request using the URL and the GET method
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonStr = response.body?.string()
                val jsonObj = JSONObject(jsonStr.toString())
                val mealsArray = jsonObj.getJSONArray("meals")
                for (i in 0 until mealsArray.length()) {
                    val mealObj = mealsArray.getJSONObject(i)

                    // Extracting the values from the meal object and store them in variables
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }
                    val ingredient10 = if (mealObj.has("strIngredient10")) {
                        val text = mealObj.getString("strIngredient10")
                        if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
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
                            if (text.trim().isEmpty()) {
                                "null"
                            } else {
                                text
                            }
                        } else {
                            "null"
                        }

                    runOnUiThread {
                        //appending the data collected records to the out textview
                        out.append(
                            "\nmeal id: $mealId \nmeal name: $mealName \nDrinkAlternate: $drinkAlternate \nCategory: $category " +
                                    "\narea: $area \ninstructions: $instructions \nmealThumb: $mealThumb \ntags: $tags " +
                                    "\nyoutubeLink: $youtubeLink \ningredient1: $ingredient1 \ningredient2: $ingredient2 " +
                                    "\ningredient3: $ingredient3 \ningredient4: $ingredient4 \ningredient5: $ingredient5 " +
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
                    }

                }
            }
        })
    }
}