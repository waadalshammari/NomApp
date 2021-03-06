package com.example.finalcapstone_nomapp.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.finalcapstone_nomapp.api.FavoriteApi
import com.example.finalcapstone_nomapp.model.FavoriteModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

private const val BASE_URL = "https://61dd3d95f60e8f0017668686.mockapi.io"




class FavoriteApiRepository (val context: Context){


    private val retrofitService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val retrofitAPi = retrofitService.create(FavoriteApi::class.java)

    suspend fun getFavoriteRecipes() = retrofitAPi.getFavoriteRecipes()

    suspend fun addToFavoriteRecipes(FavoriteBody : FavoriteModel) =
        retrofitAPi.addToFavoriteRecipes(FavoriteBody)

    suspend fun editFavoriteRecipes (id : String, FavoriteBody: FavoriteModel) =
        retrofitAPi.editFavoriteRecipes(id,FavoriteBody)

    suspend fun deleteFavoriteRecipe (id : String) = retrofitAPi.deleteFavoriteRecipe(id)


    companion object{

        @SuppressLint("StaticFieldLeak")
        private var instance : FavoriteApiRepository? = null

        fun init(context: Context){
            if (instance==null)
                instance = FavoriteApiRepository(context)
        }
        fun get () : FavoriteApiRepository {
            return instance ?: throw Exception("ApiRepository must be initialized ")
        }
    }

}