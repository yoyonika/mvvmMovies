package com.example.android.justeat.ui.movie

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.example.android.justeat.base.BaseViewModel
import com.example.android.justeat.network.MovieApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel:BaseViewModel(){
    @Inject
    lateinit var movieApi: MovieApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadMovies()
    }

    private fun loadMovies(){
        subscription = movieApi.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveMoviesListStart() }
                .doOnComplete {onRetrieveMoviesListFinish()}
                .subscribe( {onRetrieveMoviesListSuccess()},
                            {onRetrieveMoviesListError()})
    }

    override fun onCleared(){
        super.onCleared()
        subscription.dispose()
    }

    private fun onRetrieveMoviesListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveMoviesListFinish(){
        loadingVisibility.value=View.GONE
    }

    private fun onRetrieveMoviesListSuccess(){

    }

    private fun onRetrieveMoviesListError(){

    }
}