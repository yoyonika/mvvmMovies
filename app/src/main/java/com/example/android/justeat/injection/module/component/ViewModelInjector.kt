package com.example.android.justeat.injection.module.component

import com.example.android.justeat.injection.module.module.NetworkModule
import com.example.android.justeat.ui.movie.MovieListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])

interface ViewModelInjector {
    fun inject (movieListViewModel: MovieListViewModel)

    @Component.Builder
    interface Builder{
        fun build(): ViewModelInjector

        fun networkModule(networkModule:NetworkModule): Builder
    }
    
}