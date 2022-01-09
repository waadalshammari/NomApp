package com.example.finalcapstone_nomapp.api

import android.content.Context
import com.example.finalcapstone_nomapp.model.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {
       // endpoint >> path
    @GET("/recipes/complexSearch?apiKey=8eb098551f4a4ae3b551d3d397a7297c&addRecipeInformation=true")
    suspend fun getRecipes(
           @Query("diet") diet : String,
           @Query("recipe information") recipeInformation : Boolean,
           @Query("fillIngredients") fillIngredients : Boolean,
           @Query("type") type:String ,
           @Query("number") number : Int = 50

           ) : Response<FoodRecipe>
}

// :Response<FoodRecipe> not list cause it is not list >> result is the list

// الشيء المتغير من الاي بي اي وابيه يكون ثابت راح اعطيه قيمه مثل النمبر ولكن اللي احتاج داتا منه مو شيء معين
// اعطيه نوع الداتا وفي الريبوزرتي استدعيه مع النوع الموجود في الاي بي اي