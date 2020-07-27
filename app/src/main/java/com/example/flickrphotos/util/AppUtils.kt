package com.example.flickrphotos.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.flickrphotos.R
import com.github.chrisbanes.photoview.PhotoView

/**
 * This method is used to load data in ImageView using Glide
 */
fun ImageView.loadImageFromLink(link: String?) {
    if (!link.isNullOrEmpty()) {
        Glide.with(context.applicationContext)
            .load(link)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .apply(createRequestOptions())
            .into(this)
    }
}

/**
 * This method is used to create Request Options for Glide
 */
fun createRequestOptions(): RequestOptions {
    return RequestOptions()
        .placeholder(R.drawable.ic_image_grey_24dp)
        .error(R.drawable.ic_broken_image_grey_24dp)
}

/**
 * This method is used to load data in PhotoView(com.github.chrisbanes:PhotoView:2.1.4) using Glide
 */
fun PhotoView.loadImageFromLink(link: String?) {
    if (!link.isNullOrEmpty()) {
        Glide.with(context.applicationContext)
            .load(link)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate()
            .apply(createRequestOptions())
            .into(this)
    }
}

/**
 * This method is used to show Toast
 */
fun showToast(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
