package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerMovie: Observer<PagedList<MovieItem?>>

    @Mock
    private lateinit var observerTv: Observer<PagedList<TvItem?>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieItem?>

    @Mock
    private lateinit var pagedListTv: PagedList<TvItem?>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieRepository)
    }

    @Test
    fun getMovieList() {
        val dummyMovie = pagedListMovie
        Mockito.`when`(dummyMovie.size).thenReturn(20)
        val movieList = MutableLiveData<PagedList<MovieItem?>>()
        movieList.value = dummyMovie

        Mockito.`when`(movieRepository.getFavoriteMovieList()).thenReturn(movieList)
        val movie = viewModel.getMovieList().value
        Mockito.verify(movieRepository).getFavoriteMovieList()
        assertNotNull(movie)
        assertEquals(20, movie?.size)

        viewModel.getMovieList().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getTvList() {
        val dummyTv = pagedListTv
        Mockito.`when`(dummyTv.size).thenReturn(20)
        val tvList = MutableLiveData<PagedList<TvItem?>>()
        tvList.value = dummyTv

        Mockito.`when`(movieRepository.getFavoriteTvList()).thenReturn(tvList)
        val tv = viewModel.getTvList().value
        Mockito.verify(movieRepository).getFavoriteTvList()
        assertNotNull(tv)
        assertEquals(20, tv?.size)

        viewModel.getTvList().observeForever(observerTv)
        Mockito.verify(observerTv).onChanged(dummyTv)
    }

}