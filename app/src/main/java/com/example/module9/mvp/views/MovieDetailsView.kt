package com.example.module9.mvp.views

import com.example.module9.data.vos.ActorVO
import com.example.module9.data.vos.MovieVO

interface MovieDetailsView: BaseView {
    fun showMovieDetails(movie : MovieVO)
    fun showCreditsByMovie (cast: List<ActorVO>, crew : List<ActorVO>)
    fun navigateBack()
}