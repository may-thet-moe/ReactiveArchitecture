package com.example.module9.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.module9.mvp.views.MovieDetailsView

interface MovieDetailPresenter: IBasePresenter {
    fun initView (view : MovieDetailsView)
    fun onUiReadyInMovieDetail (owner : LifecycleOwner, movieID : Int)
    fun onTapBack()
}