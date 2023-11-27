package com.example.module9.mvp.views

import android.view.View
import com.example.module9.data.vos.ActorVO
import com.example.module9.data.vos.GenreVO
import com.example.module9.data.vos.MovieVO

interface MainView: BaseView {

    fun showNowPlayingMovies (nowPlayingMovies : List<MovieVO>)
    fun showPopularMovies (popularMoves : List<MovieVO>)
    fun showTopRatedMovies (topRatedMovies : List<MovieVO>)
    fun showGenres(genreList : List<GenreVO>)
    fun showMoviesByGenre(moviesByGenre : List<MovieVO>)
    fun showActors (actors: List<ActorVO>)
    fun navigateToMovieDetailsScreen(movieID : Int)
    fun navigateToMovieDetailsScreenTran(movieID : Int,transitionPair : androidx.core.util.Pair<View,String>)
}