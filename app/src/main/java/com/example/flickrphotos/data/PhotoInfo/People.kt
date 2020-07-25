package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("haspeople")
    val haspeople: Int = 0
)