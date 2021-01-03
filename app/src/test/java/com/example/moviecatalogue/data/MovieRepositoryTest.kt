package com.example.moviecatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.movie.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.DetailTvResponse
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.example.moviecatalogue.utils.PagedListUtil
import com.example.moviecatalogue.valueobject.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mockito
import org.mockito.Mockito.*

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val dummyMovieList = DataDummy.generateDummyMovieList()
    private val dummyTvList = DataDummy.generateDummyTvList()
    private val dummyMovie = DataDummy.generateDummyMovie()
    private val dummyMovieId = dummyMovie.id
    private val dummyTv = DataDummy.generateDummyTv()
    private val dummyTvId = dummyTv.id

    @Test
    fun getMovieList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieItem?>
        `when`(local.getMovieList()).thenReturn(dataSourceFactory)
        movieRepository.getMovieList()

        val movieList = Resource.success(PagedListUtil.mockPagedList(dummyMovieList))
        verify(local).getMovieList()
        assertNotNull(movieList)
        assertEquals(dummyMovieList.size.toLong(), movieList.body?.size?.toLong())
    }

    @Test
    fun getTvList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvItem?>
        `when`(local.getTvList()).thenReturn(dataSourceFactory)
        movieRepository.getTvList()

        val tvList = Resource.success(PagedListUtil.mockPagedList(dummyTvList))
        verify(local).getTvList()
        assertNotNull(tvList)
        assertEquals(dummyTvList.size.toLong(), tvList.body?.size?.toLong())
    }

    @Test
    fun getMovie() {
        val dummyMovie = MutableLiveData<DetailMovieResponse>()
        dummyMovie.value = this.dummyMovie
        `when`(local.getMovie(dummyMovieId ?: 0)).thenReturn(dummyMovie)

        val movie = LiveDataTestUtil.getValue(movieRepository.getMovie(dummyMovieId ?: 0))
        com.nhaarman.mockitokotlin2.verify(local).getMovie(dummyMovieId ?: 0)
        assertNotNull(movie.body)
        assertNotNull(movie.body?.originalTitle)
        assertEquals(this.dummyMovie.originalTitle, movie.body?.originalTitle)
    }

    @Test
    fun getTv() {
        val dummyTv = MutableLiveData<DetailTvResponse>()
        dummyTv.value = this.dummyTv
        `when`(local.getTv(dummyTvId ?: 0)).thenReturn(dummyTv)

        val tv = LiveDataTestUtil.getValue(movieRepository.getTv(dummyTvId ?: 0))
        com.nhaarman.mockitokotlin2.verify(local).getTv(dummyTvId ?: 0)
        assertNotNull(tv.body)
        assertNotNull(tv.body?.originalName)
        assertEquals(this.dummyTv.originalName, tv.body?.originalName)
    }

    @Test
    fun getFavoriteMovieList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieItem?>
        `when`(local.getFavoriteMovieList()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteMovieList()

        val movieList = Resource.success(PagedListUtil.mockPagedList(dummyMovieList))
        verify(local).getFavoriteMovieList()
        assertNotNull(movieList)
        assertEquals(dummyMovieList.size.toLong(), movieList.body?.size?.toLong())
    }

    @Test
    fun getFavoriteTvList() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvItem?>
        `when`(local.getFavoriteTvList()).thenReturn(dataSourceFactory)
        movieRepository.getFavoriteTvList()

        val tvList = Resource.success(PagedListUtil.mockPagedList(dummyTvList))
        verify(local).getFavoriteTvList()
        assertNotNull(tvList)
        assertEquals(dummyTvList.size.toLong(), tvList.body?.size?.toLong())
    }
}