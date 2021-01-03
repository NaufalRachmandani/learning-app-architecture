package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.room.MovieDao
import com.example.moviecatalogue.data.source.remote.movie.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.DetailTvResponse
import com.example.moviecatalogue.data.source.remote.tv.TvItem

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {

        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun getMovieList(): DataSource.Factory<Int, MovieItem?> = movieDao.getMovieList()

    fun getMovie(id: Int): LiveData<DetailMovieResponse?> = movieDao.getMovie(id)

    fun getFavoriteMovieList(): DataSource.Factory<Int, MovieItem?> = movieDao.getFavoriteMovieList()

    fun getTvList(): DataSource.Factory<Int, TvItem?> = movieDao.getTvList()

    fun getTv(id: Int): LiveData<DetailTvResponse?> = movieDao.getTv(id)

    fun getFavoriteTvList(): DataSource.Factory<Int, TvItem?> = movieDao.getFavoriteTvList()

    fun insertMovieList(movies: List<MovieItem?>) = movieDao.insertMovieList(movies)

    fun insertMovie(movie: DetailMovieResponse?) = movieDao.insertMovie(movie)

    fun insertTvList(tvs: List<TvItem?>) = movieDao.insertTvList(tvs)

    fun insertTv(tv: DetailTvResponse?) = movieDao.insertTv(tv)

    fun setFavoriteMovie(movie: MovieItem?, detailMovie: DetailMovieResponse?, state: Boolean?) {
        movie?.favorite = state
        detailMovie?.favorite = state
        movieDao.updateMovie(movie)
        movieDao.updateDetailMovie(detailMovie)
    }

    fun setFavoriteTv(tv: TvItem?, detailTv: DetailTvResponse?, state: Boolean?) {
        tv?.favorite = state
        detailTv?.favorite = state
        movieDao.updateTv(tv)
        movieDao.updateDetailTv(detailTv)
    }
}