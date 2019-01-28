package com.example.android.justeat.ui.movie

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.android.justeat.R
import com.example.android.justeat.base.BaseViewModel
import com.example.android.justeat.model.Movie
import com.example.android.justeat.network.MovieApi
import com.example.android.justeat.ui.movie.post.MovieListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel:BaseViewModel(){
    @Inject
    lateinit var movieApi: MovieApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val movieListAdapter:MovieListAdapter = MovieListAdapter()

    init {
        loadMovies()
    }

    private fun loadMovies(){
        subscription = movieApi.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveMoviesListStart() }
                .doOnComplete {onRetrieveMoviesListFinish()}
                .subscribe(
                        {result -> onRetrieveMoviesListSuccess(result)},
                        {onRetrieveMoviesListError()})
    }

    override fun onCleared(){
        super.onCleared()
        subscription.dispose()
    }

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadMovies()}

    private fun onRetrieveMoviesListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value= null
    }

    private fun onRetrieveMoviesListFinish(){
        loadingVisibility.value=View.GONE
    }

    private fun onRetrieveMoviesListSuccess(movieList:List<Movie>){
        movieListAdapter.updateMovieList(movieList)
    }

    private fun onRetrieveMoviesListError(){
        errorMessage.value= R.string.movie_error
    }
}