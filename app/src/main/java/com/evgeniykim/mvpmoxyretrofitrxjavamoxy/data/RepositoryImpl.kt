package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data

import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.ServerGateway
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.Configuration
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.MovieDetails
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.MoviesData
import io.reactivex.Single

class RepositoryImpl: Repository {
    override fun getConfiguration(): Single<Configuration> {
        return ServerGateway.api.getConfiguration()
    }

    override fun getPopularMovies(page: Int): Single<MoviesData> {
        return ServerGateway.api.getPopularMovieList(page = page)
    }

    override fun getMovieDetails(movieId: Long): Single<MovieDetails> {
        return ServerGateway.api.getMovieDetails(movieId = movieId)
    }
}