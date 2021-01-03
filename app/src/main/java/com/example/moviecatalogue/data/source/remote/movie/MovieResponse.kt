package com.example.moviecatalogue.data.source.remote.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("results")
    val results: List<MovieItem?>? = null
)