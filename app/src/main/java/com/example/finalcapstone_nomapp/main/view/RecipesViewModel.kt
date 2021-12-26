package com.example.finalcapstone_nomapp.main.view

import android.net.wifi.WifiManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalcapstone_nomapp.api.RecipesApi
import com.example.finalcapstone_nomapp.model.FavoriteModel
import com.example.finalcapstone_nomapp.model.FoodRecipe
import com.example.finalcapstone_nomapp.model.Result
import com.example.finalcapstone_nomapp.repository.ApiRepository
import com.example.finalcapstone_nomapp.repository.FavoriteApiRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Tag
import kotlin.Exception

private const val TAG = "RecipesViewModel"

class RecipesViewModel : ViewModel() {

    // Getting instance from ApiRepository with companion object function

    private val apiRepo = ApiRepository.get()

    private val favoriteApiRepo = FavoriteApiRepository.get()

    // livedata
    val recipesLiveData = MutableLiveData<List<Result>>()
    val recipesErrorLiveData = MutableLiveData<String>()

    // ماحط ليست ريسلت لان نبي وصفه وحده ولكل وصفه اشياء معينه تظهر لنا عكس اول اكثر من وصفه يعني نحط ليست
    var selectedRecipeMutabileLiveData = MutableLiveData<Result>()


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

    fun addFavoriteRecipe(favoriteModel : Result , note : String ){
      viewModelScope.launch (Dispatchers.IO) {
          try {
              val response = favoriteApiRepo.addToFavoriteRecipes(FavoriteModel("String",
              "String","String",0,0,"String",true))

              FirebaseAuth.getInstance().currentUser!!.uid

              if (response.isSuccessful){
                  response.body()?.run {
                      Log.d(TAG,this.toString())

                  }
              } else{
                  Log.d(TAG,"NOT SUCCESS ${response.message()}")
              }
          } catch (e : Exception){
              Log.d(TAG,e.message.toString())
          }
      }
    }

}