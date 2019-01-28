package com.example.android.justeat.ui.movie.post

import android.arch.lifecycle.MutableLiveData
import com.example.android.justeat.base.BaseViewModel
import com.example.android.justeat.model.Movie

class MovieViewModel: BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()

    fun bind(movie: Movie){
        postTitle.value = movie.title
        postBody.value = movie.overview
    }

    fun getMovieTitle():MutableLiveData<String>{
        return postTitle
    }

    fun getMovieBody():MutableLiveData<String>{
        return postBody
    }
}