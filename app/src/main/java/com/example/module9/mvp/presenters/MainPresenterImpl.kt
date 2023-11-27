package com.example.module9.mvp.presenters

import android.view.View
import androidx.core.util.Pair
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.module9.data.models.MovieModel
import com.example.module9.data.models.MovieModelImpl
import com.example.module9.data.vos.GenreVO
import com.example.module9.mvp.views.MainView

class MainPresenterImpl : ViewModel(), MainPresenter {

    //View
    var mView : MainView? = null

    //Model
    private val mMovieModel : MovieModel = MovieModelImpl

    //States
    private var mGenres : List<GenreVO>? = listOf()



    override fun initView(view: MainView) {
        mView = view
    }

    override fun onTapGenre(genrePosition: Int) {
        mGenres?.getOrNull(genrePosition)?.id?.let { genreID ->
            mMovieModel.getMoviesByGenre(genreID = genreID.toString(), onSuccess = {
                mView?.showMoviesByGenre(it)
            }, onFailure = {
                mView?.showError(it)
            })
        }
    }

    override fun onUiReady(owner: LifecycleOwner) {
        //Now Plyaing
        mMovieModel.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showNowPlayingMovies(it)
        }

        //Popular Movies
        mMovieModel.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showPopularMovies(it)
        }

        //Top Rated Movies
        mMovieModel.getTopRatedMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showTopRatedMovies(it)
        }

        //Genre and Get Movies For First Genre
        mMovieModel.getGenres(
            onSuccess = {
                mGenres = it
                mView?.showGenres(it)
                it.firstOrNull()?.id?.let { firstGenreID ->
                    onTapGenre(firstGenreID)
                }
            },
            onFailure = {
                mView?.showError(it)
            }
        )

        //Actors
        mMovieModel.getActors(
            onSuccess = {
                mView?.showActors(it)
            },
            onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapMovieFromBanner(movieID: Int) {
        mView?.navigateToMovieDetailsScreen(movieID)
    }

    override fun onTapMovieFromShowCase(movieID: Int, transitionPair: Pair<View, String>) {
        mView?.navigateToMovieDetailsScreenTran(movieID, transitionPair)
    }

//    override fun onTapMovieFromShowCase(movieID: Int) {
//        mView?.navigateToMovieDetailsScreen(movieID)
//    }

    override fun onTapChange(movieID: Int) {
        mView?.navigateToMovieDetailsScreen(movieID)
    }
}