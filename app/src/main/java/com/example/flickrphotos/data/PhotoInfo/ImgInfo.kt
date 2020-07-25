package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class ImgInfo(
    @SerializedName("photo")
    val photo: Photo = Photo(),
    @SerializedName("stat")
    val stat: String = ""
)