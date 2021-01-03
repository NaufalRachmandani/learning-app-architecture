package com.example.moviecatalogue.data.source.remote.tv

import com.google.gson.annotations.SerializedName

data class TvResponse(
    @field:SerializedName("results")
    val results: List<TvItem?>? = null
)