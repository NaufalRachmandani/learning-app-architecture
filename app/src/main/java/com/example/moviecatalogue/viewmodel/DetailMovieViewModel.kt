package com.example.moviecatalogue.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.remote.movie.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.DetailTvResponse
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import com.example.moviecatalogue.valueobject.Resource

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    val response: LiveData<String> = movieRepository.response

    fun getMovie(id: Int): LiveData<Resource<DetailMovieResponse?>> = movieRepository.getMovie(id)

    fun getTv(id: Int): LiveData<Resource<DetailTvResponse?>> = movieRepository.getTv(id)

    fun setFavoriteMovie(movie: MovieItem?, detailMovie: DetailMovieResponse?, state: Boolean?) =
        movieRepository.setFavoriteMovie(movie, detailMovie, state)

    fun setFavoriteTv(tv: TvItem?, detailTv: DetailTvResponse?, state: Boolean?) =
        movieRepository.setFavoriteTv(tv, detailTv, state)

}