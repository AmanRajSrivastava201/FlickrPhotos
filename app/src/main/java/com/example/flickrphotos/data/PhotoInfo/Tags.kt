package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Tags(
    @SerializedName("tag")
    val tag: List<Tag> = listOf()
)