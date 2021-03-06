package com.example.finalcapstone_nomapp.main.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalcapstone_nomapp.model.FavoriteModel
import com.example.finalcapstone_nomapp.model.Result
import com.example.finalcapstone_nomapp.repository.FavoriteApiRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


private const val TAG = "FavoriteRecipesViewModel"


class FavoriteRecipesViewModel : ViewModel() {

    private val apiRepo = FavoriteApiRepository.get()
    val favoriteRecipesLiveData = MutableLiveData<List<FavoriteModel>>()
    var selectedRecipeMutabileLiveData = MutableLiveData<FavoriteModel>()
    val favoriteRecipesErrorLiveData = MutableLiveData<String>()
    val editFavoriteLiveData = MutableLiveData<String>()
    val deleteFavoriteLiveData = MutableLiveData<String>()

    //=======================================================================================//
    fun callFavoriteRecipes() {

        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = apiRepo.getFavoriteRecipes()
                if (response.isSuccessful) {

                    response.body()?.run {
                        Log.d(TAG, this.toString())
                        favoriteRecipesLiveData.postValue(this)
                        Log.d(TAG, "success response ${response.body()}")

                    }
                } else {
                    Log.d(TAG, "NOT SUCCESS ${response.message()}")
                    favoriteRecipesErrorLiveData.postValue(response.message())
                }


            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                favoriteRecipesErrorLiveData.postValue(e.message.toString())
            }
        }

    }

    //=======================================================================================//
    fun editFavoriteRecipe(FavoriteBody: FavoriteModel) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                // id cause its edit we need the id of the user
                val response = apiRepo.editFavoriteRecipes(FavoriteBody.id, FavoriteBody)
                if (response.isSuccessful) {
                    response.body()?.run {
                        //  callFavoriteRecipes()
                        Log.d(TAG, this.toString())

                        editFavoriteLiveData.postValue("success response")
                    }
                } else {
                    Log.d(TAG, "Not Success ${response.message()}")
                    favoriteRecipesErrorLiveData.postValue(response.message())
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                favoriteRecipesErrorLiveData.postValue(e.message.toString())
            }
        }

    }

    //=======================================================================================//
    fun deleteFavoriteRecipe(FavoriteModel: FavoriteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.deleteFavoriteRecipe(FavoriteModel.id)
                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.string())
                        deleteFavoriteLiveData.postValue("success response")
                    }
                } else {
                    Log.d(TAG, "Not Success ${response.message()}")
                    favoriteRecipesErrorLiveData.postValue(response.message())
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
                favoriteRecipesErrorLiveData.postValue(e.message.toString())
            }
        }
    }

    //=======================================================================================//
    fun addFavoriteRecipe(favoriteModel: Result, note: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiRepo.addToFavoriteRecipes(
                    FavoriteModel(
                        favoriteModel.aggregateLikes,
                        favoriteModel.cheap,
                        favoriteModel.dairyFree,
                        favoriteModel.glutenFree,
                        favoriteModel.id.toString(),
                        favoriteModel.image,
                        favoriteModel.readyInMinutes,
                        favoriteModel.sourceUrl,
                        favoriteModel.summary,
                        favoriteModel.title,
                        favoriteModel.vegan,
                        favoriteModel.vegetarian,
                        favoriteModel.veryHealthy,
                        "",
                        ""
                    )
                )
                Log.d(TAG, "URL ${favoriteModel.sourceUrl}")

                if (response.isSuccessful) {
                    response.body()?.run {
                        Log.d(TAG, this.toString())

                    }
                } else {
                    Log.d(TAG, "NOT SUCCESS ${response.message()}")
                }
            } catch (e: Exception) {
                Log.d(TAG, e.message.toString())
            }
        }
    }

}