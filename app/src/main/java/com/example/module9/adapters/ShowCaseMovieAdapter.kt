package com.example.module9.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.module9.R
import com.example.module9.data.vos.MovieVO
import com.example.module9.delegate.ShowCaseViewHolderDelegate
import com.example.module9.viewholer.ShowCaseMovieViewHolder

class ShowCaseMovieAdapter(private val mDelegate: ShowCaseViewHolderDelegate): RecyclerView.Adapter<ShowCaseMovieViewHolder>() {

    private var mMovieList : List<MovieVO> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCaseMovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_show_case_movie,parent,false)
        return ShowCaseMovieViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ShowCaseMovieViewHolder, position: Int) {
        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }
    }

    override fun getItemCount(): Int {
       return mMovieList.count()
    }

    @SuppressLint ("NotifyDataSetChanged")
    fun setNewData (movieList : List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}