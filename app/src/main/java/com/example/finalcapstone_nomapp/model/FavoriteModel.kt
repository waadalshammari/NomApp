package com.example.finalcapstone_nomapp.model


import com.google.gson.annotations.SerializedName

data class FavoriteModel(
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("cheap")
    val cheap: Boolean,
    @SerializedName("dairyFree")
    val dairyFree: Boolean,
    @SerializedName("glutenFree")
    val glutenFree: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("ready")
    val ready: Int,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vegan")
    val vegan: Boolean,
    @SerializedName("vegetarian")
    val vegetarian: Boolean,
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean,
    @SerializedName("userid")
    val userid: String,
    @SerializedName("note")
     var note: String
)