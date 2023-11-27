package com.example.module9.viewholer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module9.R
import com.example.module9.data.vos.MovieVO
import com.example.module9.delegate.ShowCaseViewHolderDelegate
import com.example.module9.network.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_show_case_movie.view.*

class ShowCaseMovieViewHolder(itemView: View, private val mDelegate: ShowCaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mMovie : MovieVO ?= null

    init {
        itemView.setOnClickListener {
            mMovie?.let {
                it.id?.let { it1 -> mDelegate.onTapMovieFromShowCase(it1, androidx.core.util.Pair.create(
                    itemView.ivShowCaseMovie, R.string.transition_game.toString()
                )) }
            }

        }
    }

    fun bindData(movie: MovieVO){
        mMovie = movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivShowCaseMovie)
        itemView.tvShowCaseMovieName.text = movie.title
        itemView.tvShowCaseMovieDate.text = movie.releaseDate
    }
}