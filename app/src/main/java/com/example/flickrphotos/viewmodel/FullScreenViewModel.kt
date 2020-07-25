package com.example.flickrphotos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickrphotos.data.PhotoInfo.ImgInfo
import com.example.flickrphotos.data.PhotoInfo.Photo
import com.example.flickrphotos.di.DaggerViewModelComponent
import com.example.flickrphotos.di.RepositoryModule
import com.example.flickrphotos.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class FullScreenViewModel : ViewModel() {

    @Inject
    lateinit var api: ApiService

    var imgInfo = MutableLiveData<Photo>()

    /**
     * Used to initialise Dagger component
     */
    init {
        DaggerViewModelComponent.builder()
            .repositoryModule(RepositoryModule())
            .build()
            .inject(this)
    }

    /**
     * This method is used to get image information from api
     */
    fun getImgInfo(photoId: String, apiKey: String) {
        api.getImageInfo(apiKey, photoId)
            .enqueue(object : Callback<ImgInfo> {
                override fun onFailure(call: Call<ImgInfo>, t: Throwable) {
                    imgInfo.postValue(null)
                }

                override fun onResponse(call: Call<ImgInfo>, response: Response<ImgInfo>) {
                    imgInfo.postValue(response.body()?.photo)
                }
            })
    }
}