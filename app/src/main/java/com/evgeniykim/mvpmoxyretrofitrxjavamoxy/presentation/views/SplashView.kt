package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.views

import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.Configuration

interface SplashView: BaseViews {

    fun  onConfigLoaded(configuration: Configuration)
}