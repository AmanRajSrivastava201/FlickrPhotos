package com.example.flickrphotos.network

import com.example.flickrphotos.data.ImagesListModel
import com.example.flickrphotos.data.PhotoInfo.ImgInfo
import com.example.flickrphotos.util.FlickrUtil
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("rest/?method=" + FlickrUtil.METHOD_GET_RECENT + "&format=json&nojsoncallback=1")
    fun getRecentImages(
        @Query("api_key") key: String,
        @Query("page") pageNo: Int
    ): Call<ImagesListModel>

    @GET("rest/?method=" + FlickrUtil.METHOD_GET_INFO + "&format=json&nojsoncallback=1")
    fun getImageInfo(
        @Query("api_key") key: String,
        @Query("photo_id") photoId: String
    ): Call<ImgInfo>


}