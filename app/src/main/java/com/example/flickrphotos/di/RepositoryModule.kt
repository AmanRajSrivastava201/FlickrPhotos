package com.example.flickrphotos.di

import com.example.flickrphotos.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository()= Repository()
}