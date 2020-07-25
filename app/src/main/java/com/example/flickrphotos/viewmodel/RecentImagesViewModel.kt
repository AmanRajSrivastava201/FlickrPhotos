package com.example.flickrphotos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flickrphotos.data.ImagesListModel
import com.example.flickrphotos.di.DaggerViewModelComponent
import com.example.flickrphotos.di.RepositoryModule
import com.example.flickrphotos.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class RecentImagesViewModel : ViewModel() {
    @Inject
    lateinit var api: ApiService
    var recentImageList = MutableLiveData<ImagesListModel>()

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
     * Used to get recent images from api
     */
    fun getRecentImages(apiKey: String, pageNo: Int) {
        api.getRecentImages(apiKey, pageNo)
            .enqueue(object : Callback<ImagesListModel> {
                override fun onFailure(call: Call<ImagesListModel>, t: Throwable) {
                    recentImageList.postValue(null)
                }
                override fun onResponse(
                    call: Call<ImagesListModel>,
                    response: Response<ImagesListModel>
                ) {
                    recentImageList.postValue(response.body())
                }

            })
    }
}