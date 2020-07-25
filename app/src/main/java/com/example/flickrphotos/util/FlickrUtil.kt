package com.example.flickrphotos.util

import com.example.flickrphotos.BuildConfig

object FlickrUtil {
    const val API_KEY = BuildConfig.ApiKey
    const val METHOD_GET_RECENT = "flickr.photos.getRecent"
    const val METHOD_GET_INFO = "flickr.photos.getInfo"
    const val BASE_URL = "https://api.flickr.com/services/"

    /**
     * This method is used to get flickr image link
     */
    fun getFlickrImageLink(
        id: String,
        secret: String,
        serverId: String,
        farmId: Int
    ): String {
        return "https://farm$farmId.staticflickr.com/$serverId/${id}_${secret}.jpg"
    }

}