package com.example.moviecatalogue.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import splitties.activities.start

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val topAnim =
            AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val bottomAnim =
            AnimationUtils.loadAnimation(this, R.anim.bottom_animation)
        iv_logo_splashscreen.animation = topAnim
        tv_app_name.animation = bottomAnim
        val splashDelay = 3000
        Handler(Looper.getMainLooper()).postDelayed({
            start<HomeActivity>()
            finish()
        }, splashDelay.toLong())
    }
}