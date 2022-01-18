package com.example.finalcapstone_nomapp.api

import com.example.finalcapstone_nomapp.model.FavoriteModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface FavoriteApi {

    @GET ("/Favorite_Recipes")
    suspend fun getFavoriteRecipes(
       // @Query("userId") UserId: String
    ) : Response<List<FavoriteModel>>
 // body -> show data (add)
    @POST ("/Favorite_Recipes")
    suspend fun addToFavoriteRecipes(
        @Body FavoriteBody : FavoriteModel
    ) : Response<ResponseBody>
     // edit -> need data then edit
    @PUT ("/Favorite_Recipes/{id}")
    suspend fun editFavoriteRecipes(@Path("id") id : String,
    @Body FavoriteBody: FavoriteModel
    ) : Response<FavoriteModel>

    // body -> just delete dat

    @DELETE("/Favorite_Recipes/{id}")
    suspend fun deleteFavoriteRecipe (@Path("id") id : String
    ) : Response<ResponseBody>


}

 // body بس يعرض لنا داتا مثل الحدف بس يحدف داتا ماحتاج داتا تطلع لي
// التعديل الاديت نفس الشيء ماتجيب داتا انا اعدل بس عليها