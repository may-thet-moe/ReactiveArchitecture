package com.example.module9.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.module9.R
import com.example.module9.data.vos.MovieVO
import com.example.module9.delegate.MovieViewHolderDelegate
import com.example.module9.viewholer.MovieViewHolder

class MovieAdapter(private val delegate: MovieViewHolderDelegate): RecyclerView.Adapter<MovieViewHolder>() {

    private var mMovieList : List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie,parent,false)
        return MovieViewHolder(view, delegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovieList.count()
    }

    @SuppressLint("NotifyDataSetChange", "NotifyDataSetChanged")
    fun setNewData(movieList : List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}