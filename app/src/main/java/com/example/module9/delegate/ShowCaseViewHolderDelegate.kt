package com.example.module9.delegate

import android.view.View

interface ShowCaseViewHolderDelegate {

    fun onTapMovieFromShowCase(movieID : Int, transitionPair: androidx.core.util.Pair<View,String>)
}