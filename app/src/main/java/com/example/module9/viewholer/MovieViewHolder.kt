package com.example.module9.viewholer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module9.data.vos.MovieVO
import com.example.module9.delegate.MovieViewHolderDelegate
import com.example.module9.network.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.activity_movie_detail.view.*
import kotlinx.android.synthetic.main.view_holder_movie.view.*

class MovieViewHolder(itemView: View, private val mDelegate: MovieViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mMoive : MovieVO ?= null

    init {
        itemView.setOnClickListener {
            mMoive?.let {
                it.id?.let { it1 -> mDelegate.onTapChange(it1) }
            }
        }
    }

    fun bindData(movie: MovieVO){

        mMoive = movie

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(itemView.ivMovieImage)
        itemView.tvMovieImage.text = movie.title
        itemView.tvMovieRating.text = movie.voteAverage?.toString()
        itemView.rbMovieRating.rating = movie.getRatingBasedOnFiveStars()
    }
}