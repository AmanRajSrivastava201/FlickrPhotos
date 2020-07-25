package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Url(
    @SerializedName("_content")
    val content: String = "",
    @SerializedName("type")
    val type: String = ""
)