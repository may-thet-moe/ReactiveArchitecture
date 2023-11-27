package com.example.module9.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.module9.data.models.MovieModelImpl
import com.example.module9.mvp.views.MovieDetailsView

class MovieDetailPresenterImpl: ViewModel(), MovieDetailPresenter {

    //Model
    private val mMovieModel  = MovieModelImpl

    //View
    private var mView : MovieDetailsView? = null

    override fun initView(view: MovieDetailsView) {
        mView = view
    }

    override fun onUiReadyInMovieDetail(owner: LifecycleOwner, movieID: Int) {
        //Movie Details
        mMovieModel.getMovieDetails(movieID.toString()){
            mView?.showError(it)
        }?.observe(owner){
            it?.let {
                mView?.showMovieDetails(it)
            }
        }

        //Credits
        mMovieModel.getCreditsByMovie(movieID = movieID.toString(),
        onSuccess = {
            mView?.showCreditsByMovie(cast = it.first, crew = it.second)
        }, onFailure = {
            mView?.showError(it)
            })
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}