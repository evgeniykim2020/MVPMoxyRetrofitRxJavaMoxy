package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.views

import moxy.MvpView

interface BaseViews: MvpView {

    fun onLoad()

    fun OnError(error: Throwable)
}