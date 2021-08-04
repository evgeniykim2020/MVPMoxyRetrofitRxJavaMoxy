package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.R
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.Movie
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.presenters.MoviesPresenter
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.views.MoviesView
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class MoviesFragment : MvpAppCompatFragment(), OnMovieClickListener, MoviesView {

    @InjectPresenter
    lateinit var presenter: MoviesPresenter

    private var mAdapter = MoviesPagedAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getMovies(this)

    }

    override fun onMoviesLoaded(movies: PagedList<Movie>) {

        TODO("Not yet implemented")
    }

    override fun onLoad() {
        TODO("Not yet implemented")
    }

    override fun OnError(error: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onMovieClick(movie: Movie) {
        val navController = findNavController()
        val actionMoviesListToMovieDetails = MoviesFragmentDirections
            .actionMoviesFragmentToDetailsFragment()
            .setMovie(movie)
        navController.navigate(actionMoviesListToMovieDetails)
    }
}