package com.example.module9.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.module9.R
import com.example.module9.data.vos.ActorVO
import com.example.module9.viewholer.BestActorViewHolder

class BestActorAdapter:RecyclerView.Adapter<BestActorViewHolder>() {

    private var mActors : List<ActorVO> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestActorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_best_actor,parent,false)
        return BestActorViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestActorViewHolder, position: Int) {
            if(mActors.isNotEmpty()){
                holder.bindData(mActors[position])
            }
    }

    override fun getItemCount(): Int {
        return mActors.count()
    }

    @SuppressLint("NotifyDataSetChange")
    fun setNewData(actors: List<ActorVO>){
        mActors = actors
        notifyDataSetChanged()
    }
}