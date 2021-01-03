package com.example.moviecatalogue.listener

import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.TvItem

interface OnMovieClickListener {
    fun onMovieClick(movie: MovieItem?)
    fun onTvClick(tv: TvItem?)
}