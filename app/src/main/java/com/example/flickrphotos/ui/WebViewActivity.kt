package com.example.flickrphotos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.flickrphotos.R
import com.example.flickrphotos.util.IMG_URL
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        supportActionBar?.hide()
        webview.webViewClient = WebViewClient()
        val url = intent.getStringExtra(IMG_URL)
        webview.loadUrl(url)
    }
}