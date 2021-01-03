package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import com.example.moviecatalogue.valueobject.Resource

class HomeViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val response: LiveData<String> = movieRepository.response

    fun getBannerList(): LiveData<Resource<PagedList<MovieItem?>>> = movieRepository.getMovieList()

    fun getMovieList(): LiveData<Resource<PagedList<MovieItem?>>> = movieRepository.getMovieList()

    fun getTvList(): LiveData<Resource<PagedList<TvItem?>>> = movieRepository.getTvList()
}
