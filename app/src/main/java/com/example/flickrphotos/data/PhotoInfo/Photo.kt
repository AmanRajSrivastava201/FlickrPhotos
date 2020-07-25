package com.example.flickrphotos.data.PhotoInfo


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("comments")
    val comments: Comments = Comments(),
    @SerializedName("dates")
    val dates: Dates = Dates(),
    @SerializedName("dateuploaded")
    val dateuploaded: String = "",
    @SerializedName("description")
    val description: Description = Description(),
    @SerializedName("editability")
    val editability: Editability = Editability(),
    @SerializedName("farm")
    val farm: Int = 0,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("isfavorite")
    val isfavorite: Int = 0,
    @SerializedName("license")
    val license: String = "",
    @SerializedName("media")
    val media: String = "",
    @SerializedName("notes")
    val notes: Notes = Notes(),
    @SerializedName("originalformat")
    val originalformat: String = "",
    @SerializedName("originalsecret")
    val originalsecret: String = "",
    @SerializedName("owner")
    val owner: Owner = Owner(),
    @SerializedName("people")
    val people: People = People(),
    @SerializedName("publiceditability")
    val publiceditability: Publiceditability = Publiceditability(),
    @SerializedName("rotation")
    val rotation: Int = 0,
    @SerializedName("safety_level")
    val safetyLevel: String = "",
    @SerializedName("secret")
    val secret: String = "",
    @SerializedName("server")
    val server: String = "",
    @SerializedName("tags")
    val tags: Tags = Tags(),
    @SerializedName("title")
    val title: Title = Title(),
    @SerializedName("urls")
    val urls: Urls = Urls(),
    @SerializedName("usage")
    val usage: Usage = Usage(),
    @SerializedName("views")
    val views: String = "",
    @SerializedName("visibility")
    val visibility: Visibility = Visibility()
)