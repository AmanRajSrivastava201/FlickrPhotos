package com.example.flickrphotos.di

import com.example.flickrphotos.viewmodel.FullScreenViewModel
import com.example.flickrphotos.viewmodel.RecentImagesViewModel
import dagger.Component

@Component(modules = [RepositoryModule::class, ApiModule::class])
interface ViewModelComponent {
    fun inject(fullScreenViewModel: FullScreenViewModel)
    fun inject(recentImagesViewModel: RecentImagesViewModel)
}