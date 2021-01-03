package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.remote.ApiResponse
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.movie.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.DetailTvResponse
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.valueobject.Resource

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    companion object {

        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    val response: LiveData<String> = remoteDataSource.response

    fun getMovieList(): LiveData<Resource<PagedList<MovieItem?>>> {
        return object : NetworkBoundResource<PagedList<MovieItem?>, List<MovieItem?>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieItem?>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovieList(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieItem?>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieItem?>>> =
                remoteDataSource.getMovieList()

            override fun saveCallResult(data: List<MovieItem?>) {
                val movieList = ArrayList<MovieItem>()
                for (response in data) {
                    val movie = MovieItem(
                        response?.originalLanguage,
                        response?.originalTitle,
                        response?.posterPath,
                        response?.backdropPath,
                        response?.voteAverage,
                        response?.id,
                        response?.favorite
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovieList(movieList)
            }
        }.asLiveData()
    }

    fun getTvList(): LiveData<Resource<PagedList<TvItem?>>> {
        return object : NetworkBoundResource<PagedList<TvItem?>, List<TvItem?>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvItem?>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(5)
                    .setPageSize(5)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvList(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvItem?>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvItem?>>> =
                remoteDataSource.getTvList()

            override fun saveCallResult(data: List<TvItem?>) {
                val tvList = ArrayList<TvItem>()
                for (response in data) {
                    val tv = TvItem(
                        response?.originalLanguage,
                        response?.posterPath,
                        response?.backdropPath,
                        response?.originalName,
                        response?.voteAverage,
                        response?.id,
                        response?.favorite
                    )
                    tvList.add(tv)
                }

                localDataSource.insertTvList(tvList)
            }

        }.asLiveData()
    }

    fun getMovie(id: Int): LiveData<Resource<DetailMovieResponse?>> {
        return object :
            NetworkBoundResource<DetailMovieResponse?, DetailMovieResponse?>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailMovieResponse?> = localDataSource.getMovie(id)

            override fun shouldFetch(data: DetailMovieResponse?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse?>> =
                remoteDataSource.getMovie(id)

            override fun saveCallResult(data: DetailMovieResponse?) {
                localDataSource.insertMovie(data)
            }

        }.asLiveData()
    }

    fun getTv(id: Int): LiveData<Resource<DetailTvResponse?>> {
        return object :
            NetworkBoundResource<DetailTvResponse?, DetailTvResponse?>(appExecutors) {
            override fun loadFromDB(): LiveData<DetailTvResponse?> = localDataSource.getTv(id)

            override fun shouldFetch(data: DetailTvResponse?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<DetailTvResponse?>> =
                remoteDataSource.getTv(id)

            override fun saveCallResult(data: DetailTvResponse?) {
                localDataSource.insertTv(data)
            }

        }.asLiveData()
    }

    fun getFavoriteMovieList(): LiveData<PagedList<MovieItem?>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovieList(), config).build()
    }

    fun getFavoriteTvList(): LiveData<PagedList<TvItem?>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(5)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvList(), config).build()
    }

    fun setFavoriteMovie(movie: MovieItem?, detailMovie: DetailMovieResponse?, state: Boolean?) =
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteMovie(movie, detailMovie, state) }

    fun setFavoriteTv(tv: TvItem?, detailTv: DetailTvResponse?, state: Boolean?) =
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteTv(tv, detailTv, state) }
}