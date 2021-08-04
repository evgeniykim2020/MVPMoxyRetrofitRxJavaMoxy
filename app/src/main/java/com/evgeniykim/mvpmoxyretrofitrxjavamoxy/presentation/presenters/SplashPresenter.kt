package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.presenters

import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.RepositoryImpl
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.views.SplashView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class SplashPresenter: MvpPresenter<SplashView>() {

    private val mCompositeDisposable = CompositeDisposable()
    private val mRepository = RepositoryImpl()

    fun loadConfiguration() {
        mCompositeDisposable.add(
            mRepository.getConfiguration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { config -> viewState.onConfigLoaded(config) },
                    { throwable -> viewState.OnError(throwable)}
                )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
    }
}