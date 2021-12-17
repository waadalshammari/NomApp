package com.example.finalcapstone_nomapp.main.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalcapstone_nomapp.api.RecipesApi
import com.example.finalcapstone_nomapp.model.FoodRecipe
import com.example.finalcapstone_nomapp.repository.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Tag
import java.lang.Exception

private const val TAG = "RecipesViewModel"

class RecipesViewModel : ViewModel() {

    // Getting instance from ApiRepository with companion object function
    private val apiRepo = ApiRepository.get()

    // livedata
    private val recipesLiveData = MutableLiveData<List<FoodRecipe>>()
    private val recipesErrorLiveData = MutableLiveData<String>()

    fun callRecipes(){
     //coroutine in this scope will live as long the view model is alive.

        viewModelScope.launch(Dispatchers.IO) {
          // use try and catch to handle http exceptions

            try {
                val response = apiRepo.getRecipes("String",true,true,"String")
                if (response.isSuccessful){
                    response.body()?.run {
                        Log.d(TAG,this.toString())

                        recipesLiveData.postValue(FoodRecipe)
                    }
                }else{
                    Log.d(TAG,response.message())
                    recipesErrorLiveData.postValue(response.message())

                }
            } catch (e : Exception){
                Log.d(TAG,e.message.toString())
                recipesErrorLiveData.postValue(e.message.toString())
            }
        }
    }

}