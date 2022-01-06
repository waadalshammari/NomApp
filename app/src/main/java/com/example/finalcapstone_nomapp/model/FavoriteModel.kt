package com.example.finalcapstone_nomapp.model


import com.google.gson.annotations.SerializedName

data class FavoriteModel(
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("ready")
    val ready: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vegan")
    val vegan: Boolean,
    @SerializedName("userid")
    val userID: String,
    @SerializedName("note")
    var note: String
)