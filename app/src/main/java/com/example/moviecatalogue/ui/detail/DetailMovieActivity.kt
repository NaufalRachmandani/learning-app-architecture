package com.example.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.remote.movie.DetailMovieResponse
import com.example.moviecatalogue.data.source.remote.movie.MovieItem
import com.example.moviecatalogue.data.source.remote.tv.DetailTvResponse
import com.example.moviecatalogue.data.source.remote.tv.TvItem
import com.example.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.example.moviecatalogue.valueobject.Status
import com.example.moviecatalogue.viewmodel.DetailMovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {

    companion object {

        const val EXTRA_MOVIE = "movie"
        const val EXTRA_TV = "tv"
        const val EXTRA_TYPE = "type"
    }

    private lateinit var activityDetailMovieBinding: ActivityDetailMovieBinding
    private val detailMovieViewModel by viewModel<DetailMovieViewModel>()

    private var type = 0
    private var state = false
    private val movie: MovieItem by lazy { intent.getParcelableExtra(EXTRA_MOVIE) ?: MovieItem() }
    private val tv: TvItem by lazy { intent.getParcelableExtra(EXTRA_TV) ?: TvItem() }
    private var detailMovie: DetailMovieResponse? = null
    private var detailTv: DetailTvResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(activityDetailMovieBinding.root)

        initiateData()
        initiateUI()
    }

    private fun initiateData() {
        type = intent.getIntExtra(EXTRA_TYPE, 0)

        if (type == 0) {
            movie.id?.let { id ->
                detailMovieViewModel.getMovie(id).observe(this, { movie ->
                    activityDetailMovieBinding.groupResponse.visibility = View.GONE
                    activityDetailMovieBinding.groupDetail.visibility = View.VISIBLE
                    if (movie != null) {
                        when (movie.status) {
                            Status.LOADING -> activityDetailMovieBinding.progressBar.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> {
                                activityDetailMovieBinding.progressBar.visibility = View.GONE
                                detailMovie = movie.body
                                movie.body?.let { setMovieUI(it) }
                            }
                            Status.ERROR -> {
                                activityDetailMovieBinding.progressBar.visibility = View.GONE
                                //nothing to do because api response just using success
                            }
                        }
                        state = movie.body?.favorite ?: false
                        stateFavorite(state)
                    }
                })
            }
        } else {
            tv.id?.let { id ->
                detailMovieViewModel.getTv(id).observe(this, { tv ->
                    activityDetailMovieBinding.groupResponse.visibility = View.GONE
                    activityDetailMovieBinding.groupDetail.visibility = View.VISIBLE
                    if (tv != null) {
                        when (tv.status) {
                            Status.LOADING -> activityDetailMovieBinding.progressBar.visibility =
                                View.VISIBLE
                            Status.SUCCESS -> {
                                activityDetailMovieBinding.progressBar.visibility = View.GONE
                                detailTv = tv.body
                                tv.body?.let { setTvUI(it) }
                            }
                            Status.ERROR -> {
                                activityDetailMovieBinding.progressBar.visibility = View.GONE
                                //nothing to do because api response just using success
                            }
                        }
                        state = tv.body?.favorite ?: false
                        stateFavorite(state)
                    }
                })
            }
        }

        detailMovieViewModel.response.observe(this, {
            activityDetailMovieBinding.progressBar.visibility = View.GONE
            activityDetailMovieBinding.groupDetail.visibility = View.GONE
            activityDetailMovieBinding.groupResponse.visibility = View.VISIBLE
            activityDetailMovieBinding.tvResponse.text = it
        })
    }

    private fun initiateUI() {
        with(activityDetailMovieBinding) {
            toolbar.btnToolbar.setImageResource(R.drawable.ic_baseline_arrow_back_24)
            toolbar.btnFav.isEnabled = false
            toolbar.btnFav.visibility = View.VISIBLE

            toolbar.btnToolbar.setOnClickListener(this@DetailMovieActivity)
            toolbar.btnFav.setOnClickListener(this@DetailMovieActivity)
            activityDetailMovieBinding.btnTry.setOnClickListener(this@DetailMovieActivity)
        }
    }

    private fun setMovieUI(movie: DetailMovieResponse) {
        with(activityDetailMovieBinding) {
            toolbar.tvTitle.text = movie.originalTitle
            ivBanner.load("http://image.tmdb.org/t/p/w185${movie.backdropPath}")
            ivMovie.load("http://image.tmdb.org/t/p/w185${movie.posterPath}")
            tvName.text = movie.originalTitle
            tvScore.text = resources.getString(
                R.string.score,
                movie.voteAverage
            )
            tvLanguage.text = resources.getString(
                R.string.language,
                movie.originalLanguage
            )
            tvStatus.text = resources.getString(
                R.string.status,
                movie.status
            )
            tvEpisodes.text = resources.getString(
                R.string.episodes,
                1
            )
            tvPopularity.text = resources.getString(
                R.string.popularity,
                movie.popularity
            )
            tvSynopsis.text = resources.getString(
                R.string.synopsis,
                movie.overview
            )
            toolbar.btnFav.isEnabled = true
        }
    }

    private fun setTvUI(tv: DetailTvResponse) {
        with(activityDetailMovieBinding) {
            toolbar.tvTitle.text = tv.originalName
            ivBanner.load("http://image.tmdb.org/t/p/w185${tv.backdropPath}")
            ivMovie.load("http://image.tmdb.org/t/p/w185${tv.posterPath}")
            tvName.text = tv.originalName
            tvScore.text = resources.getString(
                R.string.score,
                tv.voteAverage
            )
            tvLanguage.text = resources.getString(
                R.string.language,
                tv.originalLanguage
            )
            tvStatus.text = resources.getString(
                R.string.status,
                tv.status
            )
            tvEpisodes.text = resources.getString(
                R.string.episodes,
                tv.numberOfEpisodes
            )
            tvPopularity.text = resources.getString(
                R.string.popularity,
                tv.popularity
            )
            tvSynopsis.text = resources.getString(
                R.string.synopsis,
                tv.overview
            )
            toolbar.btnFav.isEnabled = true
        }
    }

    private fun stateFavorite(state: Boolean) {
        if (state) {
            activityDetailMovieBinding.toolbar.btnFav.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            activityDetailMovieBinding.toolbar.btnFav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun favClick() {
        if (type == 0) {
            if (state) {
                detailMovieViewModel.setFavoriteMovie(movie, detailMovie, false)
            } else {
                detailMovieViewModel.setFavoriteMovie(movie, detailMovie, true)
            }
        } else {
            if (state) {
                detailMovieViewModel.setFavoriteTv(tv, detailTv, false)
            } else {
                detailMovieViewModel.setFavoriteTv(tv, detailTv, true)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            activityDetailMovieBinding.toolbar.btnToolbar.id -> onBackPressed()
            activityDetailMovieBinding.toolbar.btnFav.id -> {
                favClick()
            }
            activityDetailMovieBinding.btnTry.id -> {
                if (type == 0) {
                    movie.id?.let { it1 -> detailMovieViewModel.getMovie(it1) }
                } else {
                    tv.id?.let { it1 -> detailMovieViewModel.getTv(it1) }
                }
            }
        }
    }
}