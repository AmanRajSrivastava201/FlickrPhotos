package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Visibility(
    @SerializedName("isfamily")
    val isfamily: Int = 0,
    @SerializedName("isfriend")
    val isfriend: Int = 0,
    @SerializedName("ispublic")
    val ispublic: Int = 0
)