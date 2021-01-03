package com.example.moviecatalogue.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ui.home.MovieFragment

class HomePagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager,
    private val isFromFav: Boolean
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val tabTitles = intArrayOf(R.string.movie, R.string.tv_show)

    override fun getCount(): Int {
        return tabTitles.size
    }

    override fun getItem(position: Int): Fragment {
        return MovieFragment.newInstance(position, isFromFav)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(tabTitles[position])
    }
}