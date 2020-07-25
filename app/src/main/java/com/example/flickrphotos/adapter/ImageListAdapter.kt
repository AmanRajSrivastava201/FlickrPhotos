package com.example.flickrphotos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.flickrphotos.R
import com.example.flickrphotos.data.Photo
import com.example.flickrphotos.interfaces.LaunchFullScreen
import com.example.flickrphotos.util.FlickrUtil
import com.example.flickrphotos.util.loadImageFromLink

class ImageListAdapter(
    private val context: Context,
    private val imgList: List<Photo>,
    private val launchFullScreen: LaunchFullScreen
) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    override fun onBindViewHolder(holder: ImageListAdapter.ViewHolder, position: Int) {
        val imgUrl = FlickrUtil.getFlickrImageLink(
            imgList[position].id, imgList[position].secret, imgList[position].server,
            imgList[position].farm
        )
        holder.imgItem.loadImageFromLink(
            imgUrl
        )
        holder.imgItem.setOnClickListener {
            launchFullScreen.launchFullScreen(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgItem: ImageView = itemView.findViewById(R.id.img_item)
    }

}




