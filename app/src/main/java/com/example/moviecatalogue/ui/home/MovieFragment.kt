package com.example.moviecatalogue.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.adapter.MovieAdapter
import com.example.moviecatalogue.adapter.TvAdapter
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import com.example.moviecatalogue.databinding.FragmentMovieBinding
import com.example.moviecatalogue.listener.OnMovieClickListener
import com.example.moviecatalogue.ui.detail.DetailMovieActivity
import com.example.moviecatalogue.valueobject.Status
import com.example.moviecatalogue.viewmodel.FavoriteViewModel
import com.example.moviecatalogue.viewmodel.HomeViewModel
import com.example.moviecatalogue.viewmodel.ViewModelFactory
import splitties.fragments.start

class MovieFragment : Fragment(), OnMovieClickListener {

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"
        private const val EXTRA_FAV = "is_from_fav"

        fun newInstance(index: Int, isFromFav: Boolean): MovieFragment {
            val fragment = MovieFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_SECTION_NUMBER, index)
            bundle.putBoolean(EXTRA_FAV, isFromFav)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var movieBinding: FragmentMovieBinding
    private val homeViewModel: HomeViewModel by viewModels(factoryProducer = {
        ViewModelFactory.getInstance(
            requireActivity()
        )
    })
    private val favoriteViewModel: FavoriteViewModel by viewModels(factoryProducer = {
        ViewModelFactory.getInstance(
            requireActivity()
        )
    })
    private var index: Int = 0
    private var isFromFav: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        movieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return movieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        index = arguments?.getInt(ARG_SECTION_NUMBER, 0) ?: 0
        isFromFav = arguments?.getBoolean(EXTRA_FAV, false) ?: false

        initiateData()
    }

    private fun initiateData() {
        if (index == 0) {
            val adapter = MovieAdapter(this@MovieFragment)
            movieBinding.rvMovie.adapter = adapter
            if (isFromFav) {
                favoriteViewModel.getMovieList().observe(viewLifecycleOwner, {
                    movieBinding.groupResponse.visibility = View.GONE
                    if (it != null) {
                        adapter.submitList(it)
                        movieList()
                    }
                })
            } else {
                homeViewModel.getMovieList().observe(viewLifecycleOwner, {
                    movieBinding.groupResponse.visibility = View.GONE
                    if (it != null) {
                        when (it.status) {
                            Status.LOADING -> movieBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                movieBinding.progressBar.visibility = View.GONE
                                adapter.submitList(it.body)
                                movieList()
                            }
                            Status.ERROR -> {
                                movieBinding.progressBar.visibility = View.GONE
                                //nothing to do because api response just using success
                            }
                        }
                    }
                })
            }
        } else {
            val adapter = TvAdapter(this@MovieFragment)
            movieBinding.rvMovie.adapter = adapter
            if (isFromFav) {
                favoriteViewModel.getTvList().observe(viewLifecycleOwner, {
                    if (it != null) {
                        adapter.submitList(it)
                        tvList()
                    }
                })
            } else {
                homeViewModel.getTvList().observe(viewLifecycleOwner, {
                    movieBinding.groupResponse.visibility = View.GONE
                    if (it != null) {
                        when (it.status) {
                            Status.LOADING -> movieBinding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                movieBinding.progressBar.visibility = View.GONE
                                adapter.submitList(it.body)
                                tvList()
                            }
                            Status.ERROR -> {
                                movieBinding.progressBar.visibility = View.GONE
                                //nothing to do because api response just using success
                            }
                        }
                    }
                })
            }
        }

        homeViewModel.response.observe(viewLifecycleOwner, {
            movieBinding.progressBar.visibility = View.GONE
            movieBinding.groupResponse.visibility = View.VISIBLE
            movieBinding.tvResponse.text = it
            movieBinding.btnTry.setOnClickListener {
                if (index == 0) {
                    homeViewModel.getMovieList()
                } else {
                    homeViewModel.getTvList()
                }
                initiateData()
            }
        })
    }

    private fun movieList() {
        with(movieBinding) {
            rvMovie.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }
    }

    private fun tvList() {
        with(movieBinding) {
            rvMovie.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initiateData()
    }

    override fun onMovieClick(movie: MovieItem?) {
        start<DetailMovieActivity> {
            putExtra(DetailMovieActivity.EXTRA_MOVIE, movie)
            putExtra(DetailMovieActivity.EXTRA_TYPE, index)
        }
    }

    override fun onTvClick(tv: TvItem?) {
        start<DetailMovieActivity> {
            putExtra(DetailMovieActivity.EXTRA_TV, tv)
            putExtra(DetailMovieActivity.EXTRA_TYPE, index)
        }
    }

}