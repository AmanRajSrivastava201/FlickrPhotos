package com.example.flickrphotos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.ProgressBar
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.flickrphotos.R
import com.example.flickrphotos.adapter.ImageListAdapter
import com.example.flickrphotos.data.Photo
import com.example.flickrphotos.interfaces.LaunchFullScreen
import com.example.flickrphotos.util.FlickrUtil
import com.example.flickrphotos.util.IMG_POSITION
import com.example.flickrphotos.util.IMG_URL
import com.example.flickrphotos.viewmodel.RecentImagesViewModel
import kotlinx.android.synthetic.main.activity_recent_image.*
import kotlinx.android.synthetic.main.item_image.*
import java.io.Serializable

class RecentImageActivity : AppCompatActivity() {
    private lateinit var mRecentImageViewModel: RecentImagesViewModel
    private var mPhotosList: ArrayList<Photo> = ArrayList()
    private lateinit var mAdapter: ImageListAdapter
    private lateinit var mPullToRefresh: SwipeRefreshLayout
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mProgressNewPage: ProgressBar
    private var isScrolling: Boolean = false
    private var currentItem: Int = 0
    private var totalItems: Int = 0
    private var scrollOutItems: Int = 0
    private var initPage: Int = 1
    private var currentPage = 1
    private lateinit var manager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_image)
        initViews()
        initAdapter()
        initViewModel()

        /**
         * Used for Pagination
         */
        rec_images.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentItem = manager.childCount
                totalItems = manager.itemCount
                scrollOutItems = manager.findFirstVisibleItemPosition()

                if (isScrolling && (currentItem + scrollOutItems == totalItems)) {
                    isScrolling = false
                    currentPage += 1
                    mProgressNewPage.visibility = View.VISIBLE
                    fetchData(currentPage)
                }
            }
        })
    }

    /**
     * This method is used to initialise viewModel
     */
    private fun initViewModel() {
        mRecentImageViewModel = ViewModelProvider(this).get(RecentImagesViewModel::class.java)
        observeData()
        fetchData(initPage)
    }

    /**
     * This method is used to initialise views
     */
    private fun initViews() {
        mProgressBar = findViewById(R.id.progress_images)
        mProgressBar.visibility = View.VISIBLE
        mProgressNewPage = findViewById(R.id.progress_new_page)
        mPullToRefresh = findViewById(R.id.pullToRefresh)
        mPullToRefresh.setOnRefreshListener {
            currentPage = 1
            fetchData(initPage)
            mPullToRefresh.isRefreshing = true
        }
        manager = GridLayoutManager(this, 2)
        rec_images.layoutManager = manager
    }

    /**
     * This method is used to initialise recyclerView adapter
     */
    private fun initAdapter() {
        mAdapter = ImageListAdapter(this, mPhotosList, object : LaunchFullScreen {
            override fun launchFullScreen(imgPosition: Int) {
                openFullScreen(imgPosition)
            }

        })
        rec_images.adapter = mAdapter

    }

    /**
     * This method is used to open full screen with transition animation
     */
    private fun openFullScreen(position: Int) {
        val intent = Intent(this, FullScreenActivity::class.java)
        intent.putExtra(IMG_URL, mPhotosList as Serializable)
        intent.putExtra(IMG_POSITION, position)
        val options = ActivityOptionsCompat
            .makeSceneTransitionAnimation(this, img_item, getString(R.string.shared_element))
        startActivity(intent, options.toBundle())
    }

    /**
     * This method is used to reload data on pull down refresh
     */
    private fun fetchData(pageNo: Int) {
        mRecentImageViewModel.getRecentImages(FlickrUtil.API_KEY, pageNo)
    }

    /**
     * This method is used to observe viewModel data
     */
    private fun observeData() {
        mRecentImageViewModel.recentImageList.observe(this, Observer { imageListModel ->
            if (imageListModel != null) {
                if (currentPage != 1) {
                    mPhotosList.addAll(imageListModel.photos.photo)
                } else {
                    mPhotosList.clear()
                    mPhotosList.addAll(imageListModel.photos.photo)
                }
                mAdapter.notifyDataSetChanged()
                mPullToRefresh.isRefreshing = false
                mProgressBar.visibility = View.GONE
                mProgressNewPage.visibility = View.GONE
            } else {
                mProgressBar.visibility = View.GONE
                mProgressNewPage.visibility = View.GONE
            }
        })
    }

}