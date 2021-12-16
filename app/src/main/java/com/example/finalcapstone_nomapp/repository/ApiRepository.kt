package com.example.finalcapstone_nomapp.repository

import android.content.Context
import com.example.finalcapstone_nomapp.api.RecipesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


private const val BASE_URL = "https://api.spoonacular.com"

class ApiRepository () {

    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitApi = retrofitService.create(RecipesApi::class.java)


    suspend fun getRecipes(diet: String, recipeInfo: Boolean, fillIngredients: Boolean, type: String) = retrofitApi.getRecipes(diet
        ,recipeInfo,fillIngredients,type)

}