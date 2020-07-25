package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Comments(
    @SerializedName("_content")
    val content: String = ""
)