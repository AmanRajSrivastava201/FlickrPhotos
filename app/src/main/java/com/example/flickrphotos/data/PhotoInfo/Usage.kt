package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Usage(
    @SerializedName("canblog")
    val canblog: Int = 0,
    @SerializedName("candownload")
    val candownload: Int = 0,
    @SerializedName("canprint")
    val canprint: Int = 0,
    @SerializedName("canshare")
    val canshare: Int = 0
)