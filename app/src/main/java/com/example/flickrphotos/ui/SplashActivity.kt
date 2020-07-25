package com.example.flickrphotos.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.flickrphotos.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler().postDelayed({
            startApp()
        }, 1500)
    }

    /**
     * This method is used to start recent images screen
     */
    private fun startApp() {
        val intent = Intent(this, RecentImageActivity::class.java)
        startActivity(intent)
        finish()

    }
}

