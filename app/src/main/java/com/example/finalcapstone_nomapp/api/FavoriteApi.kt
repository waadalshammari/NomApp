package com.example.finalcapstone_nomapp.api

import androidx.room.Delete
import com.example.finalcapstone_nomapp.model.FavoriteModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface FavoriteApi {

    @GET ("/Favorite_Recipes")
    suspend fun getFavoriteRecipes(
       // @Query("userId") UserId: String
    ) : Response<List<FavoriteModel>>

    @POST ("/Favorite_Recipes")
    suspend fun addToFavoriteRecipes(
        @Body FavoriteBody : FavoriteModel
    ) : Response<ResponseBody>

    @PUT ("/Favorite_Recipes/{id}")
    suspend fun editFavoriteRecipes(@Path("id") id : String,
    @Body FavoriteBody: FavoriteModel
    ) : Response<FavoriteModel>

    @DELETE("/Favorite_Recipes/{id}")
    suspend fun deleteFavoriteRecipe (@Path("id") id : String
    ) : Response<ResponseBody>


}