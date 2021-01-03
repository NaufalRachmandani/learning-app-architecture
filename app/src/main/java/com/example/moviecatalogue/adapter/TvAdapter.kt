package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import com.example.moviecatalogue.databinding.ItemMovieBinding
import com.example.moviecatalogue.listener.OnMovieClickListener

class TvAdapter(
    private val listener: OnMovieClickListener
) :
    PagedListAdapter<TvItem, TvAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvItem>() {
            override fun areItemsTheSame(oldItem: TvItem, newItem: TvItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvItem, newItem: TvItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvAdapter.ViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: TvAdapter.ViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    fun getSwipedData(swipedPosition: Int): TvItem? = getItem(swipedPosition)

    inner class ViewHolder(private val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(tv: TvItem) {
            with(itemMovieBinding) {
                ivMovie.load("http://image.tmdb.org/t/p/w185${tv.posterPath}")
                tvName.text = tv.originalName
                tvScore.text = itemView.resources.getString(
                    R.string.score,
                    tv.voteAverage
                )
                tvLanguage.text = itemView.resources.getString(
                    R.string.language,
                    tv.originalLanguage
                )

                itemView.setOnClickListener { listener.onTvClick(tv) }
            }
        }
    }
}