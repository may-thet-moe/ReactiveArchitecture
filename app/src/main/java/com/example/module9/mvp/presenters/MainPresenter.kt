package com.example.module9.mvp.presenters

import com.example.module9.delegate.BannerViewHolderDelegate
import com.example.module9.delegate.MovieViewHolderDelegate
import com.example.module9.delegate.ShowCaseViewHolderDelegate
import com.example.module9.mvp.views.MainView

interface MainPresenter: IBasePresenter, BannerViewHolderDelegate,
    ShowCaseViewHolderDelegate, MovieViewHolderDelegate {

        fun initView(view : MainView)
        fun onTapGenre(genrePosition : Int)
}