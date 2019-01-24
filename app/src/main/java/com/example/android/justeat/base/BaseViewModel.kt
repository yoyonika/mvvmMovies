package com.example.android.justeat.base

import android.arch.lifecycle.ViewModel
import com.example.android.justeat.injection.module.component.ViewModelInjector
import com.example.android.justeat.injection.module.module.NetworkModule
import com.example.android.justeat.ui.movie.MovieListViewModel

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    private fun inject(){
        when (this) {
            is MovieListViewModel -> injector.inject(this)
        }
    }
}