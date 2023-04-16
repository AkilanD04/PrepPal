package com.example.preppal

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout file for this activity
        setContentView(R.layout.activity_main)

        // Hide the action bar
        supportActionBar?.hide()

        // Find the ImageView in the layout and load an image into it using Glide
        val imageView = findViewById<ImageView>(R.id.imageView3)
        Glide.with(this).load(R.drawable.img1).into(imageView)

        // Find the buttons in the layout and set click listeners for each of them
        val addbtn = findViewById<Button>(R.id.ADD)
        val search_ingre = findViewById<Button>(R.id.search_ingredient)
        val search = findViewById<Button>(R.id.Search)
        val search_more = findViewById<Button>(R.id.search_more)

        // Create an instance of the AppDatabase and get the MealDao
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "MealDatabase").build()
        val mealDao = db.mealDao()

        // Set a click listener for the "ADD" button that adds a meal to the database
        addbtn.setOnClickListener {
            add(mealDao, this)
        }

        // Set a click listener for the "search_ingredient" button that starts the Ingredient_search activity
        search_ingre.setOnClickListener {
            val newact = Intent(this, Ingredient_search::class.java)
            startActivity(newact)
        }

        // Set a click listener for the "Search" button that starts the Search_food activity
        search.setOnClickListener {
            val next = Intent(this, Search_food::class.java)
            startActivity(next)
        }

        // Set a click listener for the "search_more" button that starts the Search_more activity
        search_more.setOnClickListener {
            val next = Intent(this, Search_more::class.java)
            startActivity(next)
        }
    }


    private fun add(mealDao: MealDao, context: Context) {
        // Launch a coroutine in a blocking manner
        runBlocking {
            // Launch a new coroutine to handle the database operation
            launch {
                //meal data from the cw
                val meal1 = arrayOf(
                    "52949",
                    "Sweet and Sour Pork".lowercase(), "null", "Pork", "Chinese",
                    "Preparation\\r\\n1. Crack the egg into a bowl. Separate the egg white and yolk.\\r\\n\\r\\nSweet and Sour Pork\\r\\n2. Slice the pork tenderloin into ips.\\r\\n\\r\\n3. Prepare the marinade using a pinch of salt, one teaspoon of starch, two teaspoons of light soy sauce, and an egg white.\\r\\n\\r\\n4. Marinade the pork ips for about 20 minutes.\\r\\n\\r\\n5. Put the remaining starch in a bowl. Add some water and vinegar to make a starchy sauce.\\r\\n\\r\\nSweet and Sour Pork\\r\\nCooking Inuctions\\r\\n1. Pour the cooking oil into a wok and heat to 190\\u00b0C (375\\u00b0F). Add the marinated pork ips and fry them until they turn brown. Remove the cooked pork from the wok and place on a plate.\\r\\n\\r\\n2. Leave some oil in the wok. Put the tomato sauce and white sugar into the wok, and heat until the oil and sauce are fully combined.\\r\\n\\r\\n3. Add some water to the wok and thoroughly heat the sweet and sour sauce before adding the pork ips to it.\\r\\n\\r\\n4. Pour in the starchy sauce. Stir-fry all the ingredients until the pork and sauce are thoroughly mixed together.\\r\\n\\r\\n5. Serve on a plate and add some coriander for decoration.",
                    "https://www.themealdb.com/images/media/meals/1529442316.jpg",
                    "Sweet",
                    "https://www.youtube.com/watch?v=mdaBIhgEAMo",
                    "Pork".lowercase(),
                    "Egg".lowercase(),
                    "Water".lowercase(),
                    "Salt".lowercase(),
                    "Sugar".lowercase(),
                    "Soy Sauce".lowercase(),
                    "Starch".lowercase(),
                    "Tomato Puree".lowercase(),
                    "Vinegar".lowercase(),
                    "Coriander".lowercase(),
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "200g",
                    "1",
                    "Dash",
                    "1/2 tsp",
                    "1 tsp",
                    "10g",
                    "10g",
                    "30g",
                    "10g",
                    "Dash",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                )
                val meal2 = arrayOf(
                    "52920",
                    "Chicken Marengo".lowercase(),
                    "null",
                    "Chicken",
                    "French",
                    "Heat the oil in a large flameproof casserole dish and stir-fry the mushrooms until they start to soften. Add the chicken legs and cook briefly on each side to colour them a little.\\r\\nPour in the passata, crumble in the stock cube and stir in the olives. Season with black pepper \\u2013 you shouldn\\u2019t need salt. Cover and simmer for 40 mins until the chicken is tender. Sprinkle with parsley and serve with pasta and a salad, or mash and green veg, if you like.",
                    "https://www.themealdb.com/images/media/meals/qpxvuq1511798906.jpg",
                    "null",
                    "https://www.youtube.com/watch?v=U33HYUr-0Fw",
                    "Olive Oil".lowercase(),
                    "Mushrooms".lowercase(),
                    "Chicken Legs".lowercase(),
                    "Passata".lowercase(),
                    "Chicken Stock Cube".lowercase(),
                    "Black Olives".lowercase(),
                    "Parsley".lowercase(),
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "1 tbs",
                    "300g",
                    "4",
                    "500g",
                    "1",
                    "100g ",
                    "Chopped",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                )
                val meal3 = arrayOf(
                    "52997",
                    "Beef Banh Mi Bowls with Sriracha Mayo, Carrot & Pickled Cucumber".lowercase(),
                    "null",
                    "Beef",
                    "Vietnamese",
                    "Add'l ingredients: mayonnaise, siracha\\r\\n\\r\\n1\\r\\n\\r\\nPlace rice in a fine-mesh sieve and rinse until water runs clear. Add to a small pot with 1 cup water (2 cups for 4 servings) and a pinch of salt. Bring to a boil, then cover and reduce heat to low. Cook until rice is tender, 15 minutes. Keep covered off heat for at least 10 minutes or until ready to serve.\\r\\n\\r\\n2\\r\\n\\r\\nMeanwhile, wash and dry all produce. Peel and finely chop garlic. Zest and quarter lime (for 4 servings, zest 1 lime and quarter both). Trim and halve cucumber lengthwise; thinly slice crosswise into half-moons. Halve, peel, and medium dice onion. Trim, peel, and grate carrot.\\r\\n\\r\\n3\\r\\n\\r\\nIn a medium bowl, combine cucumber, juice from half the lime, \\u00bc tsp sugar (\\u00bd tsp for 4 servings), and a pinch of salt. In a small bowl, combine mayonnaise, a pinch of garlic, a squeeze of lime juice, and as much sriracha as you\\u2019d like. Season with salt and pepper.\\r\\n\\r\\n4\\r\\n\\r\\nHeat a drizzle of oil in a large pan over medium-high heat. Add onion and cook, stirring, until softened, 4-5 minutes. Add beef, remaining garlic, and 2 tsp sugar (4 tsp for 4 servings). Cook, breaking up meat into pieces, until beef is browned and cooked through, 4-5 minutes. Stir in soy sauce. Turn off heat; taste and season with salt and pepper.\\r\\n\\r\\n5\\r\\n\\r\\nFluff rice with a fork; stir in lime zest and 1 TBSP butter. Divide rice between bowls. Arrange beef, grated carrot, and pickled cucumber on top. Top with a squeeze of lime juice. Drizzle with sriracha mayo.",
                    "https://www.themealdb.com/images/media/meals/z0ageb1583189517.jpg",
                    "null".lowercase(),
                    "null".lowercase(),
                    "Rice".lowercase(),
                    "Onion".lowercase(),
                    "Lime".lowercase(),
                    "Garlic Clove".lowercase(),
                    "Cucumber".lowercase(),
                    "Carrots".lowercase(),
                    "Ground Beef".lowercase(),
                    "Soy Sauce".lowercase(),
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "White",
                    "1",
                    "1",
                    "3",
                    "1",
                    "3 oz ",
                    "1 lb",
                    "2 oz ",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                )
                val meal4 = arrayOf(
                    "52973",
                    "Leblebi Soup".lowercase(),
                    "null",
                    "Vegetarian",
                    "Tunisian",
                    "Heat the oil in a large pot. Add the onion and cook until translucent.\r\nDrain the soaked chickpeas and add them to the pot together with the vegetable stock. Bring to the boil, then reduce the heat and cover. Simmer for 30 minutes.\r\nIn the meantime toast the cumin in a small ungreased frying pan, then grind them in a mortar. Add the garlic and salt and pound to a fine paste.\r\nAdd the paste and the harissa to the soup and simmer until the chickpeas are tender, about 30 minutes.\r\nSeason to taste with salt, pepper and lemon juice and serve hot.",
                    "https://www.themealdb.com/images/media/meals/x2fw9e1560460636.jpg",
                    "Soup",
                    "https://www.youtube.com/watch?v=BgRifcCwinY",
                    "Olive Oil".lowercase(),
                    "Onion".lowercase(),
                    "Chickpeas".lowercase(),
                    "Vegetable Stock".lowercase(),
                    "Cumin".lowercase(),
                    "Garlic".lowercase(),
                    "Salt".lowercase(),
                    "Harissa Spice".lowercase(),
                    "Pepper".lowercase(),
                    "Lime".lowercase(),
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "2 tbs",
                    "1 medium finely diced",
                    "250g",
                    "1.5L",
                    "1 tsp ",
                    "5 cloves",
                    "1/2 tsp",
                    "1 tsp ",
                    "Pinch",
                    "1/2 ",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                    "null",
                )
                // Create a new instance of the Meals data class and initialize its properties using values from the meal1 array
                val _meal1 = Meals(
                    meal1[0].toInt(),
                    Meal = meal1[1],
                    DrinkAlternate = meal1[2],
                    Category = meal1[3],
                    Area = meal1[4],
                    Instructions = meal1[5],
                    MealThumb = meal1[6],
                    Tags = meal1[7],
                    YoutubeLink = meal1[8],
                    Ingredient1 = meal1[9],
                    Ingredient2 = meal1[10],
                    Ingredient3 = meal1[11],
                    Ingredient4 = meal1[12],
                    Ingredient5 = meal1[13],
                    Ingredient6 = meal1[14],
                    Ingredient7 = meal1[15],
                    Ingredient8 = meal1[16],
                    Ingredient9 = meal1[17],
                    Ingredient10 = meal1[18],
                    Ingredient11 = meal1[19],
                    Ingredient12 = meal1[20],
                    Ingredient13 = meal1[21],
                    Ingredient14 = meal1[22],
                    Ingredient15 = meal1[23],
                    Ingredient16 = meal1[24],
                    Ingredient17 = meal1[25],
                    Ingredient18 = meal1[26],
                    Ingredient19 = meal1[27],
                    Ingredient20 = meal1[28],
                    Measure1 = meal1[29],
                    Measure2 = meal1[30],
                    Measure3 = meal1[31],
                    Measure4 = meal1[32],
                    Measure5 = meal1[33],
                    Measure6 = meal1[34],
                    Measure7 = meal1[35],
                    Measure8 = meal1[36],
                    Measure9 = meal1[37],
                    Measure10 = meal1[38],
                    Measure11 = meal1[39],
                    Measure12 = meal1[40],
                    Measure13 = meal1[41],
                    Measure14 = meal1[42],
                    Measure15 = meal1[43],
                    Measure16 = meal1[44],
                    Measure17 = meal1[45],
                    Measure18 = meal1[46],
                    Measure19 = meal1[46],
                    Measure20 = meal1[48],
                )
                //creating for meal2, meal3 and meal4
                val _meal2 = Meals(
                    meal2[0].toInt(),
                    Meal = meal2[1],
                    DrinkAlternate = meal2[2],
                    Category = meal2[3],
                    Area = meal2[4],
                    Instructions = meal2[5],
                    MealThumb = meal2[6],
                    Tags = meal2[7],
                    YoutubeLink = meal2[8],
                    Ingredient1 = meal2[9],
                    Ingredient2 = meal2[10],
                    Ingredient3 = meal2[11],
                    Ingredient4 = meal2[12],
                    Ingredient5 = meal2[13],
                    Ingredient6 = meal2[14],
                    Ingredient7 = meal2[15],
                    Ingredient8 = meal2[16],
                    Ingredient9 = meal2[17],
                    Ingredient10 = meal2[18],
                    Ingredient11 = meal2[19],
                    Ingredient12 = meal2[20],
                    Ingredient13 = meal2[21],
                    Ingredient14 = meal2[22],
                    Ingredient15 = meal2[23],
                    Ingredient16 = meal2[24],
                    Ingredient17 = meal2[25],
                    Ingredient18 = meal2[26],
                    Ingredient19 = meal2[27],
                    Ingredient20 = meal2[28],
                    Measure1 = meal2[29],
                    Measure2 = meal2[30],
                    Measure3 = meal2[31],
                    Measure4 = meal2[32],
                    Measure5 = meal2[33],
                    Measure6 = meal2[34],
                    Measure7 = meal2[35],
                    Measure8 = meal2[36],
                    Measure9 = meal2[37],
                    Measure10 = meal2[38],
                    Measure11 = meal2[39],
                    Measure12 = meal2[40],
                    Measure13 = meal2[41],
                    Measure14 = meal2[42],
                    Measure15 = meal2[43],
                    Measure16 = meal2[44],
                    Measure17 = meal2[45],
                    Measure18 = meal2[46],
                    Measure19 = meal2[47],
                    Measure20 = meal2[48],
                )
                val _meal3 = Meals(
                    meal3[0].toInt(),
                    Meal = meal3[1],
                    DrinkAlternate = meal3[2],
                    Category = meal3[3],
                    Area = meal3[4],
                    Instructions = meal3[5],
                    MealThumb = meal3[6],
                    Tags = meal3[7],
                    YoutubeLink = meal3[8],
                    Ingredient1 = meal3[9],
                    Ingredient2 = meal3[10],
                    Ingredient3 = meal3[11],
                    Ingredient4 = meal3[12],
                    Ingredient5 = meal3[13],
                    Ingredient6 = meal3[14],
                    Ingredient7 = meal3[15],
                    Ingredient8 = meal3[16],
                    Ingredient9 = meal3[17],
                    Ingredient10 = meal3[18],
                    Ingredient11 = meal3[19],
                    Ingredient12 = meal3[20],
                    Ingredient13 = meal3[21],
                    Ingredient14 = meal3[22],
                    Ingredient15 = meal3[23],
                    Ingredient16 = meal3[24],
                    Ingredient17 = meal3[25],
                    Ingredient18 = meal3[26],
                    Ingredient19 = meal3[27],
                    Ingredient20 = meal3[28],
                    Measure1 = meal3[29],
                    Measure2 = meal3[30],
                    Measure3 = meal3[31],
                    Measure4 = meal3[32],
                    Measure5 = meal3[33],
                    Measure6 = meal3[34],
                    Measure7 = meal3[35],
                    Measure8 = meal3[36],
                    Measure9 = meal3[37],
                    Measure10 = meal3[38],
                    Measure11 = meal3[39],
                    Measure12 = meal3[40],
                    Measure13 = meal3[41],
                    Measure14 = meal3[42],
                    Measure15 = meal3[43],
                    Measure16 = meal3[44],
                    Measure17 = meal3[45],
                    Measure18 = meal3[46],
                    Measure19 = meal3[47],
                    Measure20 = meal3[48],
                )
                val _meal4 = Meals(
                    meal4[0].toInt(),
                    Meal = meal4[1],
                    DrinkAlternate = meal4[2],
                    Category = meal4[3],
                    Area = meal4[4],
                    Instructions = meal4[5],
                    MealThumb = meal4[6],
                    Tags = meal4[7],
                    YoutubeLink = meal4[8],
                    Ingredient1 = meal4[9],
                    Ingredient2 = meal4[10],
                    Ingredient3 = meal4[11],
                    Ingredient4 = meal4[12],
                    Ingredient5 = meal4[13],
                    Ingredient6 = meal4[14],
                    Ingredient7 = meal4[15],
                    Ingredient8 = meal4[16],
                    Ingredient9 = meal4[17],
                    Ingredient10 = meal4[18],
                    Ingredient11 = meal4[19],
                    Ingredient12 = meal4[20],
                    Ingredient13 = meal4[21],
                    Ingredient14 = meal4[22],
                    Ingredient15 = meal4[23],
                    Ingredient16 = meal4[24],
                    Ingredient17 = meal4[25],
                    Ingredient18 = meal4[26],
                    Ingredient19 = meal4[27],
                    Ingredient20 = meal4[28],
                    Measure1 = meal4[29],
                    Measure2 = meal4[30],
                    Measure3 = meal4[31],
                    Measure4 = meal4[32],
                    Measure5 = meal4[33],
                    Measure6 = meal4[34],
                    Measure7 = meal4[35],
                    Measure8 = meal4[36],
                    Measure9 = meal4[37],
                    Measure10 = meal4[38],
                    Measure11 = meal4[39],
                    Measure12 = meal4[40],
                    Measure13 = meal4[41],
                    Measure14 = meal4[42],
                    Measure15 = meal4[43],
                    Measure16 = meal4[44],
                    Measure17 = meal4[45],
                    Measure18 = meal4[46],
                    Measure19 = meal4[47],
                    Measure20 = meal4[48],
                )
                try {
                    // Try to insert the meals into the database using the mealDao
                    mealDao.insertUsers(_meal1, _meal2, _meal3, _meal4)
                    // Log a message indicating that the meals were added successfully
                    Log.d("activity", "added")
                    // Display a short toast message indicating that the meals were added successfully
                    Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    // If an error occurs while inserting the meals, log an error message
                    Log.d("activity", "error")
                    // Display a short toast message indicating that there was an error while adding the meals
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}