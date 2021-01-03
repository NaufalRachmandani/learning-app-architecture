package com.example.moviecatalogue.data.source.remote.tv

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tv_item")
@Parcelize
data class TvItem(

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("original_name")
    val originalName: String? = null,

    @field:SerializedName("vote_average")
    val voteAverage: Double? = null,

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int? = null,

    var favorite: Boolean? = false
): Parcelable