package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("author")
    val author: String = "",
    @SerializedName("authorname")
    val authorname: String = "",
    @SerializedName("_content")
    val content: String = "",
    @SerializedName("id")
    val id: String = "",
    @SerializedName("machine_tag")
    val machineTag: Boolean = false,
    @SerializedName("raw")
    val raw: String = ""
)