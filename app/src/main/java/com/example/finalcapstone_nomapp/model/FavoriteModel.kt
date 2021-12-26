package com.example.finalcapstone_nomapp.model


import com.google.gson.annotations.SerializedName

data class FavoriteModel(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("ready")
    val ready: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("vegan")
    val vegan: Boolean
)