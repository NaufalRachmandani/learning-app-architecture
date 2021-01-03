package com.example.moviecatalogue.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.opensooq.pluto.base.PlutoAdapter
import com.opensooq.pluto.base.PlutoViewHolder
import com.opensooq.pluto.listeners.OnItemClickListener

class BannerAdapter(
    items: List<MovieItem>,
    onItemClickListener: OnItemClickListener<MovieItem>? = null
) : PlutoAdapter<MovieItem, BannerAdapter.ViewHolder>(items.toMutableList(), onItemClickListener) {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent, R.layout.item_banner)
    }

    class ViewHolder(parent: ViewGroup, itemLayoutId: Int) :
        PlutoViewHolder<MovieItem>(parent, itemLayoutId) {

        private val ivBanner: ImageView = getView(R.id.iv_banner)
        private val tvScore: TextView = getView(R.id.tv_score)
        private val tvName: TextView = getView(R.id.tv_name)

        override fun set(item: MovieItem, position: Int) {
            ivBanner.load("https://image.tmdb.org/t/p/w185${item.backdropPath}") {
                crossfade(true)
                placeholder(R.drawable.ic_baseline_image_24)
            }
            tvScore.text = item.voteAverage?.toString()
            tvName.text = item.originalTitle
        }
    }
}