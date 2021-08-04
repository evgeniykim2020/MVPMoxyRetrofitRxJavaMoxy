package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data

import android.content.res.Configuration
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.MovieDetails
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.MoviesData
import io.reactivex.Single

interface Repository {

    fun getConfiguration(): Single<Configuration>
    fun getPopularMovies(page: Int): Single<MoviesData>
    fun getMovieDetails(movieId: Long): Single<MovieDetails>
}