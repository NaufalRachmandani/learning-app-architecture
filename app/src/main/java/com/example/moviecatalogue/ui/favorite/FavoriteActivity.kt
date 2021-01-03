package com.example.moviecatalogue.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviecatalogue.R
import com.example.moviecatalogue.adapter.HomePagerAdapter
import com.example.moviecatalogue.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var activityFavoriteBinding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)

        initiateUI()
    }

    private fun initiateUI() {
        val homePagerAdapter = HomePagerAdapter(this, supportFragmentManager, true)
        activityFavoriteBinding.viewpager.adapter = homePagerAdapter
        activityFavoriteBinding.tabs.setupWithViewPager(activityFavoriteBinding.viewpager)

        with(activityFavoriteBinding) {
            toolbar.btnToolbar.setImageResource(R.drawable.ic_baseline_arrow_back_24)
            toolbar.btnToolbar.setOnClickListener {
                onBackPressed()
            }
            toolbar.tvTitle.text = getString(R.string.favorite_title)
        }

    }
}