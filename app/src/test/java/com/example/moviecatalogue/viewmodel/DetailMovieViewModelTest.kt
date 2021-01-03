package com.example.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.remote.movie.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.tv.DetailTvResponse
import com.example.moviecatalogue.utils.DataDummy
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
class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel

    private val dummyMovie = DataDummy.generateDummyMovie()
    private val dummyMovieId = dummyMovie.id
    private val dummyTv = DataDummy.generateDummyTv()
    private val dummyTvId = dummyTv.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observerMovie: Observer<Resource<DetailMovieResponse?>>

    @Mock
    private lateinit var observerTv: Observer<Resource<DetailTvResponse?>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = Resource.success(this.dummyMovie)
        val movie = MutableLiveData<Resource<DetailMovieResponse?>>()
        movie.value = dummyMovie as Resource<DetailMovieResponse?>

        `when`(movieRepository.getMovie(dummyMovieId ?: 0)).thenReturn(movie)

        viewModel.getMovie(dummyMovieId ?: 0).observeForever(observerMovie)

        verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getTv() {
        val dummyTv = Resource.success(this.dummyTv)
        val movie = MutableLiveData<Resource<DetailTvResponse?>>()
        movie.value = dummyTv as Resource<DetailTvResponse?>

        `when`(movieRepository.getTv(dummyTvId ?: 0)).thenReturn(movie)

        viewModel.getTv(dummyTvId ?: 0).observeForever(observerTv)

        verify(observerTv).onChanged(dummyTv)
    }
}