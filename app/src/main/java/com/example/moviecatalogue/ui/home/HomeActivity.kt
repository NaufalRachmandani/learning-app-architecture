package com.example.moviecatalogue.ui.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.moviecatalogue.adapter.BannerAdapter
import com.example.moviecatalogue.adapter.HomePagerAdapter
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.databinding.ActivityHomeBinding
import com.example.moviecatalogue.ui.detail.DetailMovieActivity
import com.example.moviecatalogue.ui.favorite.FavoriteActivity
import com.example.moviecatalogue.valueobject.Status
import com.example.moviecatalogue.viewmodel.HomeViewModel
import com.opensooq.pluto.listeners.OnItemClickListener
import org.koin.android.viewmodel.ext.android.viewModel
import splitties.activities.start

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityHomeBinding: ActivityHomeBinding
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)

        initiateData()
        initiateUI()
    }

    private fun initiateData() {
        homeViewModel.getBannerList().observe(this, {
            activityHomeBinding.groupResponse.visibility = View.GONE
            if (it != null) {
                when (it.status) {
                    Status.LOADING -> activityHomeBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        activityHomeBinding.progressBar.visibility = View.GONE
                        val banners = it.body?.filterNotNull()?.toMutableList()
                        banners?.let { it1 -> showBanner(it1) }
                    }
                    Status.ERROR -> {
                        activityHomeBinding.progressBar.visibility = View.GONE
                        //nothing to do because api response just using success
                    }
                }
            }
        })

        homeViewModel.response.observe(this, {
            activityHomeBinding.progressBar.visibility = View.GONE
            activityHomeBinding.groupResponse.visibility = View.VISIBLE
            activityHomeBinding.tvResponse.text = it
        })
    }

    private fun initiateUI() {
        val homePagerAdapter = HomePagerAdapter(this, supportFragmentManager, false)
        activityHomeBinding.viewpager.adapter = homePagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewpager)

        with(activityHomeBinding) {
            toolbar.btnFav.visibility = View.VISIBLE
            toolbar.btnFav.setOnClickListener(this@HomeActivity)
            btnTry.setOnClickListener(this@HomeActivity)
        }

    }

    private fun showBanner(banner: MutableList<MovieItem>) {
        val bannerAdapter = BannerAdapter(banner, object : OnItemClickListener<MovieItem> {
            override fun onItemClicked(item: MovieItem?, position: Int) {
                start<DetailMovieActivity> {
                    putExtra(DetailMovieActivity.EXTRA_MOVIE, item)
                }
            }
        })

        //pass the lifecycle to make the slider aware of lifecycle to avoid memory leak and handling the pause/destroy/resume
        activityHomeBinding.sliderView.create(bannerAdapter, lifecycle = lifecycle)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            activityHomeBinding.btnTry.id -> {
                homeViewModel.getBannerList()
                initiateData()
            }
            activityHomeBinding.toolbar.btnFav.id -> start<FavoriteActivity>()
        }
    }
}