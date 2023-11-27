package com.example.module9.viewholer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module9.data.vos.MovieVO
import com.example.module9.delegate.BannerViewHolderDelegate
import com.example.module9.network.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_item_banner.view.*

class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {
    private var mMovie : MovieVO ? = null

    init {
        itemView.setOnClickListener {
            mMovie?.let {
                it.id?.let { it1 -> mDelegate.onTapMovieFromBanner(it1) }
                //mDelegate.onTapMovieFromBanner(it.id)
            }

        }
    }

    fun bindData (movie : MovieVO){
        this.mMovie = movie

        Glide.with(itemView.context)
            .load("${IMAGE_BASE_URL}${movie.posterPath}")
            .into(itemView.ivBannerImage)

        itemView.tvBannerMovieName.text = movie.title
    }
}