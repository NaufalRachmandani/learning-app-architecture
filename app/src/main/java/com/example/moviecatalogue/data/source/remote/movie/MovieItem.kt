package com.example.moviecatalogue.data.source.remote.movie

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie_item")
@Parcelize
data class MovieItem(

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null,

    var favorite: Boolean? = false
): Parcelable