package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Editability(
    @SerializedName("canaddmeta")
    val canaddmeta: Int = 0,
    @SerializedName("cancomment")
    val cancomment: Int = 0
)