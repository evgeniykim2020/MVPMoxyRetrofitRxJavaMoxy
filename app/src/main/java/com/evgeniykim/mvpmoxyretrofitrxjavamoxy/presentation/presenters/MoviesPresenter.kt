package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.presenters

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.RepositoryImpl
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.Movie
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.views.MoviesView
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.ui.movies.MoviesDataSourceFactory
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MoviesPresenter: MvpPresenter<MoviesView>() {

    private val mCompositeDisposable = CompositeDisposable()
    private val mRepository = RepositoryImpl()
    private var mMoviesPagedListLiveData: LiveData<PagedList<Movie>>? = null

    fun getMovies(owner: LifecycleOwner) {
        val moviesDataSourceFactory = MoviesDataSourceFactory(mCompositeDisposable, mRepository)
        val moviePagedListConfig = PagedList.Config.Builder()
            .setPageSize(20)
            .setEnablePlaceholders(false)
            .build()
        mMoviesPagedListLiveData = LivePagedListBuilder(moviesDataSourceFactory, moviePagedListConfig).build()
        mMoviesPagedListLiveData!!.observe(owner, Observer { movies -> viewState.onMoviesLoaded(movies) })
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }
}