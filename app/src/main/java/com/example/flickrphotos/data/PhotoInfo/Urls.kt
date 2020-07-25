package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Urls(
    @SerializedName("url")
    val url: List<Url> = listOf()
)