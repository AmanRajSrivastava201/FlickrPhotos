package com.example.flickrphotos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.flickrphotos.R
import com.example.flickrphotos.data.Photo
import com.example.flickrphotos.interfaces.LaunchWebViewScreen
import com.example.flickrphotos.interfaces.StartDownloadImage
import com.example.flickrphotos.util.FlickrUtil
import com.example.flickrphotos.util.loadImageFromLink
import com.github.chrisbanes.photoview.PhotoView

class FullScreenAdapter(
    private val context: Context,
    private val imgList: List<Photo>,
    private val launchWebViewScreen: LaunchWebViewScreen,
    private val startDownloadImage: StartDownloadImage
) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return imgList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.full_item, null)
        val imgView: PhotoView = view.findViewById(R.id.full_image)
        val imgUrl = FlickrUtil.getFlickrImageLink(
            imgList[position].id, imgList[position].secret, imgList[position].server,
            imgList[position].farm
        )
        imgView.loadImageFromLink(imgUrl)
        val btnVisitWebsite: Button = view.findViewById(R.id.visit_website)
        btnVisitWebsite.setOnClickListener {
            launchWebViewScreen.launchWebView(imgUrl)
        }
        val btnDownload: Button = view.findViewById(R.id.btnDownload)
        btnDownload.setOnClickListener {
            startDownloadImage.startDownload(imgUrl, imgList[position].id)
        }
        val viewPager = container as ViewPager
        viewPager.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val v: View = `object` as View
        viewPager.removeView(v)
    }
}