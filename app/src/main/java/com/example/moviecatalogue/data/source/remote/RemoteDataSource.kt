package com.example.moviecatalogue.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.source.remote.movie.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.movie.MovieResponse
import com.example.moviecatalogue.data.source.remote.tv.DetailTvResponse
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import com.example.moviecatalogue.data.source.remote.tv.TvResponse
import com.example.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val client: ApiService) {

    companion object {

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(client: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(client)
            }
    }

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> = _response

    fun getMovieList(): LiveData<ApiResponse<List<MovieItem?>>> {
        EspressoIdlingResource.increment()
        val listMovie = MutableLiveData<ApiResponse<List<MovieItem?>>>()
        client.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    EspressoIdlingResource.decrement()
                    listMovie.postValue(ApiResponse.success(response.body()?.results))
                } else {
                    _response.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _response.postValue("Failed to load")
            }

        })
        return listMovie
    }

    fun getTvList(): LiveData<ApiResponse<List<TvItem?>>> {
        EspressoIdlingResource.increment()
        val listTv = MutableLiveData<ApiResponse<List<TvItem?>>>()
        client.getTvList().enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                if (response.isSuccessful) {
                    EspressoIdlingResource.decrement()
                    listTv.postValue(ApiResponse.success(response.body()?.results))
                } else {
                    _response.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                _response.postValue("Failed to load")
            }

        })
        return listTv
    }

    fun getMovie(id: Int): LiveData<ApiResponse<DetailMovieResponse?>> {
        EspressoIdlingResource.increment()
        val movie = MutableLiveData<ApiResponse<DetailMovieResponse?>>()
        client.getMovie(id).enqueue(object : Callback<DetailMovieResponse> {
            override fun onResponse(
                call: Call<DetailMovieResponse>,
                response: Response<DetailMovieResponse>
            ) {
                if (response.isSuccessful) {
                    EspressoIdlingResource.decrement()
                    movie.postValue(ApiResponse.success(response.body()))
                } else {
                    _response.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                _response.postValue("Failed to load")
            }
        })

        return movie
    }

    fun getTv(id: Int): LiveData<ApiResponse<DetailTvResponse?>> {
        EspressoIdlingResource.increment()
        val tv = MutableLiveData<ApiResponse<DetailTvResponse?>>()
        client.getTv(id).enqueue(object : Callback<DetailTvResponse> {
            override fun onResponse(
                call: Call<DetailTvResponse>,
                response: Response<DetailTvResponse>
            ) {
                if (response.isSuccessful) {
                    EspressoIdlingResource.decrement()
                    tv.postValue(ApiResponse.success(response.body()))
                } else {
                    _response.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<DetailTvResponse>, t: Throwable) {
                _response.postValue("Failed to load")
            }
        })
        return tv
    }
}