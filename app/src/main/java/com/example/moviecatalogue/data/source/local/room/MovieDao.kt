package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.source.remote.movie.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.DetailTvResponse
import com.example.moviecatalogue.data.source.remote.tv.TvItem

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_item")
    fun getMovieList(): DataSource.Factory<Int, MovieItem?>

    @Query("SELECT * FROM detail_movie where id = :id")
    fun getMovie(id: Int): LiveData<DetailMovieResponse?>

    @Query("SELECT * FROM movie_item where favorite = 1")
    fun getFavoriteMovieList(): DataSource.Factory<Int, MovieItem?>

    @Query("SELECT * FROM tv_item")
    fun getTvList(): DataSource.Factory<Int, TvItem?>

    @Query("SELECT * FROM detail_tv where id = :id")
    fun getTv(id: Int): LiveData<DetailTvResponse?>

    @Query("SELECT * FROM tv_item where favorite = 1")
    fun getFavoriteTvList(): DataSource.Factory<Int, TvItem?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies: List<MovieItem?>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: DetailMovieResponse?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvList(tvs: List<TvItem?>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tv: DetailTvResponse?)

    @Update
    fun updateMovie(movie: MovieItem?)

    @Update
    fun updateDetailMovie(movie: DetailMovieResponse?)

    @Update
    fun updateTv(tv: TvItem?)

    @Update
    fun updateDetailTv(tv: DetailTvResponse?)
}