package com.example.moviecatalogue.data.source.remote

import com.example.moviecatalogue.data.source.remote.movie.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.movie.MovieResponse
import com.example.moviecatalogue.data.source.remote.tv.DetailTvResponse
import com.example.moviecatalogue.data.source.remote.tv.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/popular")
    fun getMovieList(): Call<MovieResponse>

    @GET("tv/popular")
    fun getTvList(): Call<TvResponse>

    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: Int
    ): Call<DetailMovieResponse>

    @GET("tv/{id}")
    fun getTv(
        @Path("id") id: Int
    ): Call<DetailTvResponse>

}