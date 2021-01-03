package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.TvItem

class FavoriteViewModel (private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovieList(): LiveData<PagedList<MovieItem?>> = movieRepository.getFavoriteMovieList()

    fun getTvList(): LiveData<PagedList<TvItem?>> = movieRepository.getFavoriteTvList()
}