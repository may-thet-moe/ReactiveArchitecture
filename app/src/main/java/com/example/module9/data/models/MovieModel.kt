package com.example.module9.data.models

import androidx.lifecycle.LiveData
import com.example.module9.data.vos.ActorVO
import com.example.module9.data.vos.GenreVO
import com.example.module9.data.vos.MovieVO
import io.reactivex.rxjava3.core.Observable

interface MovieModel {

    fun getNowPlayingMovies(
        onFailure : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getPopularMovies(
       onFailure : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getTopRatedMovies(
        onFailure : (String) -> Unit
    ): LiveData<List<MovieVO>>?

    fun getGenres(
        onSuccess : (List<GenreVO>) -> Unit,
        onFailure : (String) -> Unit
    )

    fun getMoviesByGenre(
        genreID : String,
        onSuccess : (List<MovieVO>) -> Unit,
        onFailure : (String) -> Unit
    )

    fun getActors(
        onSuccess : (List<ActorVO>) -> Unit,
        onFailure : (String) -> Unit
    )

    fun getMovieDetails(
        movieID : String,
        onFailure : (String) -> Unit
    ): LiveData<MovieVO?>?

    fun getCreditsByMovie(
        movieID: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun searchMovie(
        query: String
    ):Observable<List<MovieVO>>
}