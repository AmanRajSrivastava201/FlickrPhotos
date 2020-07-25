package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Notes(
    @SerializedName("note")
    val note: List<Any> = listOf()
)