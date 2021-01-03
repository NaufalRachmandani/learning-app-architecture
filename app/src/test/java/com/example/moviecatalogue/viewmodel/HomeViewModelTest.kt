package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import com.example.moviecatalogue.valueobject.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<PagedList<MovieItem?>>>

    @Mock
    private lateinit var observerTv: Observer<Resource<PagedList<TvItem?>>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieItem?>

    @Mock
    private lateinit var pagedListTv: PagedList<TvItem?>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(movieRepository)
    }

    @Test
    fun getBannerList() {
        val dummyMovie = Resource.success(pagedListMovie)
        `when`(dummyMovie.body?.size).thenReturn(20)
        val movieList = MutableLiveData<Resource<PagedList<MovieItem?>>>()
        movieList.value = dummyMovie

        `when`(movieRepository.getMovieList()).thenReturn(movieList)
        val movie = viewModel.getBannerList().value?.body
        verify(movieRepository).getMovieList()
        assertNotNull(movie)
        assertEquals(20, movie?.size)

        viewModel.getBannerList().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getMovieList() {
        val dummyMovie = Resource.success(pagedListMovie)
        `when`(dummyMovie.body?.size).thenReturn(20)
        val movieList = MutableLiveData<Resource<PagedList<MovieItem?>>>()
        movieList.value = dummyMovie

        `when`(movieRepository.getMovieList()).thenReturn(movieList)
        val movie = viewModel.getMovieList().value?.body
        verify(movieRepository).getMovieList()
        assertNotNull(movie)
        assertEquals(20, movie?.size)

        viewModel.getMovieList().observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getTvList() {
        val dummyTv = Resource.success(pagedListTv)
        `when`(dummyTv.body?.size).thenReturn(20)
        val tvList = MutableLiveData<Resource<PagedList<TvItem?>>>()
        tvList.value = dummyTv

        `when`(movieRepository.getTvList()).thenReturn(tvList)
        val tv = viewModel.getTvList().value?.body
        verify(movieRepository).getTvList()
        assertNotNull(tv)
        assertEquals(20, tv?.size)

        viewModel.getTvList().observeForever(observerTv)
        verify(observerTv).onChanged(dummyTv)
    }
}