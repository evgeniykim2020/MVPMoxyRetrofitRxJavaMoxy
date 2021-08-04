package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.ui.movies

import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.Repository
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.RepositoryImpl
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MoviesDataSource(private val mCompositeDisposable: CompositeDisposable, private val mRepositoryImpl: RepositoryImpl): ItemKeyedDataSource<Int, Movie>() {

    private var pageCount = 1
    private var page = 0


    override fun getKey(item: Movie): Int {
        return 0
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Movie>) {
        mCompositeDisposable.add(mRepositoryImpl.getPopularMovies(++page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies ->
                if (movies != null) {
                    pageCount = movies.totalPages
                    if (movies.movieList != null) {
                        callback.onResult(movies.movieList)
                    }
                }
            }, {_ -> }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Movie>) {

    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Movie>) {
        mCompositeDisposable.add(mRepositoryImpl.getPopularMovies(1)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movies ->
                if (movies != null) {
                    pageCount = movies.totalPages
                    if (movies.movieList != null) {
                        callback.onResult(movies.movieList)
                    }
                }
            }, { _ -> }))
    }



}

class MoviesDataSourceFactory(private val mCompositeDisposable: CompositeDisposable, private val mRepository: RepositoryImpl): DataSource.Factory<Int, Movie>() {
    override fun create(): DataSource<Int, Movie> {
        return MoviesDataSource(mCompositeDisposable, mRepository)
    }

}