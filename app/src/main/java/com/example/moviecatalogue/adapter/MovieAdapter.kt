package com.example.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.databinding.ItemMovieBinding
import com.example.moviecatalogue.listener.OnMovieClickListener

class MovieAdapter(
    private val listener: OnMovieClickListener
) :
    PagedListAdapter<MovieItem, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val itemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieItem? = getItem(swipedPosition)

    inner class ViewHolder(private val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {

        fun bind(movie: MovieItem) {
            with(itemMovieBinding) {
                ivMovie.load("http://image.tmdb.org/t/p/w185${movie.posterPath}")
                tvName.text = movie.originalTitle
                tvScore.text = itemView.resources.getString(
                    R.string.score,
                    movie.voteAverage
                )
                tvLanguage.text = itemView.resources.getString(
                    R.string.language,
                    movie.originalLanguage
                )

                itemView.setOnClickListener { listener.onMovieClick(movie) }
            }
        }
    }
}