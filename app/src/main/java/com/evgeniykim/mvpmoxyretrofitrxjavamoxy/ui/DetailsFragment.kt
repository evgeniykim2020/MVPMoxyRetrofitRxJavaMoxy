package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.R
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.MovieDetails
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.presenters.DetailsPresenter
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.views.DetailsView
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.utils.ImageLoadUtils
import kotlinx.android.synthetic.main.fragment_details.*
import moxy.InjectViewState
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class DetailsFragment: MvpAppCompatFragment(), DetailsView {

    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            val args = DetailsFragmentArgs.fromBundle(requireArguments())
            val movie = args.movie
            movie.let { presenter.loadDetails(it) }
        }
    }

    override fun onDetailsLoaded(movieDetails: MovieDetails) {
        movieDetails.backdropPath?.let { ImageLoadUtils.loadBigImage(it, imageViewBack) }
        movieDetails.posterPath?.let { ImageLoadUtils.loadImage(it, imageViewCover) }
        textViewTitle.text = movieDetails.title
        textViewAverage.text = movieDetails.voteAverage.toString()
        textViewBudget.text = String.format("Budget: $%d", movieDetails.budget)
        textViewOverView.text = movieDetails.overview
        textViewRuntime.text = String.format("%d min.", movieDetails.runtime)
    }

    override fun onLoad() {
        TODO("Not yet implemented")
    }

    override fun OnError(error: Throwable) {
        TODO("Not yet implemented")
    }
}