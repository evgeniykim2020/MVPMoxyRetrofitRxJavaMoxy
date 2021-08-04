package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.views

import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.MovieDetails

interface DetailsView: BaseViews {

    fun onDetailsLoaded(movieDetails: MovieDetails)
}