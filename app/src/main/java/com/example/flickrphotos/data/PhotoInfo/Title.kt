package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("_content")
    val content: String = ""
)