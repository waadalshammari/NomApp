package com.example.finalcapstone_nomapp.api

import com.example.finalcapstone_nomapp.model.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesApi {
       // endpoint >> path
    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
           @Query("diet") diet : String,
           @Query("recipe information") recipeInformation : Boolean,
           @Query("fillIngredients") fillIngredients : Boolean,
           @Query("type") type:String,
           @Query("number") number : Int = 1

           ) : Response<List<FoodRecipe>>
}
// let us create something like hash map to add all our query once
// QueryMap queries : Map<String,String>

// الشيء المتغير من الاي بي اي وابيه يكون ثابت راح اعطيه قيمه مثل النمبر ولكن اللي احتاج داتا منه مو شيء معين
// اعطيه نوع الداتا وفي الريبوزرتي استدعيه مع النوع الموجود في الاي بي اي