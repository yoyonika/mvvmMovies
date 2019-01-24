package com.example.android.justeat.network

import com.example.android.justeat.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/discover/movie?api_key=b81549e79fa118bb8ddc65ab7c9bf578")
    fun getMovies() : Observable<List<Movie>>

    @GET("/3/search/movie?api_key=b81549e79fa118bb8ddc65ab7c9bf578")
    fun searchMovies(@Query("query") title:String): Observable<List<Movie>>
}