package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("iconfarm")
    val iconfarm: Int = 0,
    @SerializedName("iconserver")
    val iconserver: String = "",
    @SerializedName("location")
    val location: Any? = null,
    @SerializedName("nsid")
    val nsid: String = "",
    @SerializedName("path_alias")
    val pathAlias: String = "",
    @SerializedName("realname")
    val realname: String = "",
    @SerializedName("username")
    val username: String = ""
)