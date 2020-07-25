package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Dates(
    @SerializedName("lastupdate")
    val lastupdate: String = "",
    @SerializedName("posted")
    val posted: String = "",
    @SerializedName("taken")
    val taken: String = "",
    @SerializedName("takengranularity")
    val takengranularity: Int = 0,
    @SerializedName("takenunknown")
    val takenunknown: String = ""
)