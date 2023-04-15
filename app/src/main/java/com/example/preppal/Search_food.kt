package com.example.preppal

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class Search_food : AppCompatActivity() {
    private lateinit var out: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_food)
        out = findViewById(R.id.meals_out)
        out.text = " "
        val name_in = findViewById<EditText>(R.id.name_in)
        val add_btn = findViewById<Button>(R.id.add)
        add_btn.setOnClickListener {
            if (name_in.text.isEmpty()) {
                Toast.makeText(this, "Please enter something ", Toast.LENGTH_SHORT).show()
            } else {
                if (name_in.text.toString().toIntOrNull() == null){
                    add_name(name_in,this)
                }
                else{
                    Toast.makeText(this, "Enter a valid ingredient/name", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun add_name(name_in: EditText, context: Context) {
        out.text = ""
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "MealDatabase").build()
        val mealDao = db.mealDao()
        runBlocking {
            launch {
                val meal_list: List<Meals> = mealDao.id_return(name_in.text.toString())
                if (meal_list.isEmpty()){
                    runOnUiThread {
                        Toast.makeText(context, "Ingredient not found", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    for (m in meal_list) {
                        out.append(
                            "\nmeal id: ${m.id} \nmeal name: ${m.Meal} \nDrinkAlternate: ${m.DrinkAlternate} \nCategory: ${m.Category} \narea: ${m.Area} \ninstructions: ${m.Instructions}" +
                                    "\nmeal thumb: ${m.MealThumb} \ntags: ${m.Tags} \nyoutubeLink: ${m.YoutubeLink} \ningredient1: ${m.Ingredient1} \ningredient2: ${m.Ingredient2}" +
                                    "\ningredient3: ${m.Ingredient3} \ningredient4: ${m.Ingredient4} \ningredient5: ${m.Ingredient5} \ningredient6: ${m.Ingredient6} \ningredient7: ${m.Ingredient6}" +
                                    "\ningredient8: ${m.Ingredient8} \ningredient9: ${m.Ingredient9} \ningredient10: ${m.Ingredient10} \ningredient11: ${m.Ingredient11} \ningredient12: ${m.Ingredient12}" +
                                    "\ningredient13: ${m.Ingredient13} \ningredient14: ${m.Ingredient14} \ningredient15: ${m.Ingredient15} \ningredient16: ${m.Ingredient16} \ningredient17: ${m.Ingredient17}" +
                                    "\ningredient18: ${m.Ingredient18} \ningredient19: ${m.Ingredient19} \ningredient20: ${m.Ingredient20} \nmeasure1: ${m.Measure1} \nmeasure2: ${m.Measure2} \nmeasure3: ${m.Measure3}" +
                                    "\nmeasure4: ${m.Measure4} \nmeasure5: ${m.Measure5} \nmeasure6: ${m.Measure6} \nmeasure7: ${m.Measure7} \nmeasure8: ${m.Measure8} \nmeasure9: ${m.Measure9} \nmeasure10: ${m.Measure10}" +
                                    "\nmeasure11: ${m.Measure11} \nmeasure12: ${m.Measure12} \nmeasure13: ${m.Measure13} \nmeasure14: ${m.Measure14} \nmeasure15: ${m.Measure15} \nmeasure16: ${m.Measure16}" +
                                    "\nmeasure17: ${m.Measure17} \nmeasure18: ${m.Measure18} \nmeasure19: ${m.Measure19} \nmeasure20: ${m.Measure20}"
                        )
                        out.append("\n\n")
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("out",out.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        out.append(savedInstanceState.getString("out"))
    }
}