package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.presenters

import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.RepositoryImpl
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.Movie
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.views.DetailsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class DetailsPresenter: MvpPresenter<DetailsView>() {

    private val mCompositeDisposable = CompositeDisposable()
    private val mmRepository = RepositoryImpl()

    fun loadDetails(movie: Movie) {
        mCompositeDisposable.add(mmRepository.getMovieDetails(
            movie.id
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movieDetails ->
                viewState.onDetailsLoaded(movieDetails)
            }, { throwable ->
                viewState.OnError(throwable)
            })
        )
    }
}