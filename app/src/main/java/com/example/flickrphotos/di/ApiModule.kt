package com.example.flickrphotos.di

import com.example.flickrphotos.repository.Repository
import com.example.flickrphotos.util.FlickrUtil
import dagger.Module
import dagger.Provides

@Module
class ApiModule {
    @Provides
    fun getApi(repository: Repository) = repository.getApi(FlickrUtil.BASE_URL)
}