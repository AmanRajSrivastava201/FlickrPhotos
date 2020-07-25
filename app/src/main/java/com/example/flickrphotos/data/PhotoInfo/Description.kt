package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("_content")
    val content: String = ""
)