package com.example.module9.viewholer

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.module9.data.vos.ActorVO
import com.example.module9.network.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_best_actor.view.*

class BestActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(actor : ActorVO){
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actor.profilePath}")
            .into(itemView.ivBestActor)
        itemView.tvBestActorName.text = actor.name
    }
}