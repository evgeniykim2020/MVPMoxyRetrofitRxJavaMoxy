package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.views

import androidx.paging.PagedList
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.Movie

interface MoviesView: BaseViews {

    fun onMoviesLoaded(movies: PagedList<Movie>)
}