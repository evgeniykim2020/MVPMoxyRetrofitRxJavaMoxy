package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api

import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("configuration")
    @Headers("Content-Type:application/json; charset+UTF-8")
    fun getConfiguration(@Query("api_key") apiKey: String = ServerGateway.API_KEY): Single<Configuration>

    @GET("genre/movie/list")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getGenreMoveList(@Query("api_key") apiKey: String = ServerGateway.API_KEY): Single<Genre>

    @GET("discover/movie")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getMoveListByGenre(
        @Query("api_key") apiKey: String = ServerGateway.API_KEY, @Query("page") page: Int,
        @Query("wiht_genres") genres: String
    ): Single<MoviesData>

    @GET("genre/tv/list")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getGenreTvList(@Query("api_key") apiKey: String = ServerGateway.API_KEY): Single<Genre>

    @GET("movie/popular")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getPopularMovieList(@Query("api_key") apiKey: String = ServerGateway.API_KEY, @Query("page") page: Int): Single<MoviesData>

    @GET("movie/{movie_id}")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getMovieDetails(@Path("movie_id") movieId: Long, @Query("api_key") apiKey: String = ServerGateway.API_KEY): Single<MovieDetails>

    @GET("movie/{movie_id}/images")
    fun getMovieImages(
        @Path("movie_id") movieId: Long, @Query("api_key") apiKey: String = ServerGateway.API_KEY, @Query(
            "include_image_language"
        ) languages: String
    ): Single<ImageData>

    @GET("configuration/languages")
    fun getLanguages(@Query("api_key") apiKey: String = ServerGateway.API_KEY): List<Language>

}