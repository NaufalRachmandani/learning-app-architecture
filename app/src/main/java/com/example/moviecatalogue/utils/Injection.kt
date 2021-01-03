package com.example.moviecatalogue.utils

import android.content.Context
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.room.MovieDatabase
import com.example.moviecatalogue.data.source.remote.ApiConfig
import com.example.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): MovieRepository {

        val client = ApiConfig.getApiService(context)
        val remoteDataSource = RemoteDataSource.getInstance(client)

        val movieDao = MovieDatabase.getInstance(context).movieDao()
        val localDataSource = LocalDataSource.getInstance(movieDao)

        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}