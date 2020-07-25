package com.example.flickrphotos.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.flickrphotos.R
import com.example.flickrphotos.adapter.FullScreenAdapter
import com.example.flickrphotos.data.Photo
import com.example.flickrphotos.interfaces.LaunchWebViewScreen
import com.example.flickrphotos.interfaces.StartDownloadImage
import com.example.flickrphotos.util.FlickrUtil
import com.example.flickrphotos.util.IMG_POSITION
import com.example.flickrphotos.util.IMG_URL
import com.example.flickrphotos.util.showToast
import com.example.flickrphotos.viewmodel.FullScreenViewModel
import java.io.File
import java.io.FileOutputStream

class FullScreenActivity : AppCompatActivity() {
    private lateinit var mViewPager: ViewPager
    private lateinit var mImgList: ArrayList<Photo>
    private var mImgPosition: Int = 0
    private lateinit var mFullScreenAdapter: FullScreenAdapter
    private lateinit var mFullScreenViewModel: FullScreenViewModel
    private lateinit var mTitleTxtView: TextView
    private lateinit var mDescTxtView: TextView
    private lateinit var mProgress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)
        if (savedInstanceState == null) {
            getDataFromIntent()
        }
        initViews()
        initAdapter()
        mViewPager.setCurrentItem(mImgPosition, true)
        getImgInfo(mImgPosition)

        /**
         * Used to fetch data on viewPager page hange
         */
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                getImgInfo(position)
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * This method is used to initialise views
     */
    private fun initViews() {
        supportActionBar?.title = "ImageDetail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setBackgroundDrawable(this.getDrawable(R.drawable.ic_baseline_navigate_next_24))
        mProgress = findViewById(R.id.progress)
        mTitleTxtView = findViewById(R.id.title)
        mDescTxtView = findViewById(R.id.description)
        mFullScreenViewModel = ViewModelProvider(this).get(FullScreenViewModel::class.java)
        mViewPager = findViewById(R.id.viewpager)
    }

    override fun onBackPressed() {
        supportFinishAfterTransition()
    }

    /**
     * This method is used to Observe ViewModel
     */
    private fun observeViewModel() {
        mFullScreenViewModel.imgInfo.observe(this, Observer { photoInfo ->
            if (photoInfo != null) {
                if (photoInfo.title.content != "") {
                    mTitleTxtView.text = photoInfo.title.content
                }
                if (photoInfo.description.content != "") {
                    mDescTxtView.text = photoInfo.description.content
                }
                mProgress.visibility = View.GONE
            }
        })
    }

    /**
     * This method is used to get image info from flickr
     */
    private fun getImgInfo(position: Int) {
        mProgress.visibility = View.VISIBLE
        clearFields()
        observeViewModel()
        mFullScreenViewModel.getImgInfo(
            mImgList[position].id,
            FlickrUtil.API_KEY
        )

    }

    /**
     * This method is used to Initialise recyclerview adapter
     */
    private fun initAdapter() {
        mFullScreenAdapter = FullScreenAdapter(this, mImgList, object : LaunchWebViewScreen {
            override fun launchWebView(imgUrl: String) {
                openWebView(imgUrl)
            }

        }, object : StartDownloadImage {
            override fun startDownload(imgUrl: String, imgId: String) {
                mProgress.visibility = View.VISIBLE
                downloadImage(imgUrl, imgId)
            }
        })
        mViewPager.adapter = mFullScreenAdapter
    }

    /**
     * This method is used to get data from intent
     */
    private fun getDataFromIntent() {
        mImgList = intent.getSerializableExtra(IMG_URL) as ArrayList<Photo>
        mImgPosition = intent.getIntExtra(IMG_POSITION, 0)
    }

    /**
     * This method is used to open webView screen
     */
    private fun openWebView(imgUrl: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(IMG_URL, imgUrl)
        startActivity(intent)
    }

    /**
     * This method is used to download image on download button click
     */
    private fun downloadImage(imgUrl: String, fileName: String) {
        Glide.with(this)
            .asBitmap()
            .load(imgUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    saveImageBitmap(resource, fileName)
                }
            })

    }

    /**
     * This method is used to save image into storage
     */
    private fun saveImageBitmap(bitmap: Bitmap, fileName: String) {
        try {
            var file = this.getExternalFilesDir(null) as File
            if (!file.exists()) {
                file.mkdir()
            }
            file = File(file, "$fileName.jpg")
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
            out.flush()
            out.close()
            mProgress.visibility = View.GONE
            showToast(
                this.getString(R.string.img_saved_successfully),
                this
            )
        } catch (e: Exception) {
            mProgress.visibility = View.GONE
            showToast(
                this.getString(R.string.failed_save_img),
                this
            )
        }
    }

    /**
     * This method is used to clear textView when viewPager selected page position change
     * Title, Description are being set from Activity since its another api call
     * imgInfo always have previous data till new data gets loaded since its liveData so clearing it in this method
     * else same data will get loaded till no new data received from api
     */
    private fun clearFields() {
        mTitleTxtView.text = ""
        mDescTxtView.text = ""
        mFullScreenViewModel.imgInfo.value = null
    }
}
