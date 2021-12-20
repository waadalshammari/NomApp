package com.example.finalcapstone_nomapp.main.view

import android.net.wifi.WifiManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalcapstone_nomapp.api.RecipesApi
import com.example.finalcapstone_nomapp.model.FoodRecipe
import com.example.finalcapstone_nomapp.model.Result
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
    val recipesLiveData = MutableLiveData<List<Result>>()
    val recipesErrorLiveData = MutableLiveData<String>()

    //=========================================================//
   // var recipesResponse : MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun callRecipes(){
     //coroutine in this scope will live as long the view model is alive.

        viewModelScope.launch(Dispatchers.IO) {
          // use try and catch to handle http exceptions

            try {
                val response = apiRepo.getRecipes("String",true,true,"String")
                if (response.isSuccessful){
                    response.body()?.run {
                        Log.d(TAG,this.toString())
                        // ask mohammed >>>> right or wrong
                        recipesLiveData.postValue(this.results)
                        Log.d(TAG, "success response ${response.body()}")
                    }
                }else{
                    Log.d(TAG,"NOT SUCCESS ${response.message()}")
                    recipesErrorLiveData.postValue(response.message())

                }
            } catch (e : Exception){
                Log.d(TAG,e.message.toString())
                recipesErrorLiveData.postValue(e.message.toString())
            }
        }
    }

}