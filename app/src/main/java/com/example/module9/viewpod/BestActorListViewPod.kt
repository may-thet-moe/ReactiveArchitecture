package com.example.module9.viewpod

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module9.adapters.BestActorAdapter
import com.example.module9.data.vos.ActorVO
import kotlinx.android.synthetic.main.view_pod_best_actor_list.view.*

class BestActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var bestActorAdapter : BestActorAdapter

    override fun onFinishInflate() {

        setUPBestActorList()
        super.onFinishInflate()
    }

    private fun setUPBestActorList() {
        bestActorAdapter = BestActorAdapter()
        rvBestActors.adapter = bestActorAdapter
        rvBestActors.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }

    fun setUpActorViewPod(backgroundColorReference: Int, title: String, moreTitleText: String){
        setBackgroundColor(ContextCompat.getColor(context,backgroundColorReference))
        tvBestActors.text = title
        tvMoreActors.text = moreTitleText
    }

    fun setData(actors : List<ActorVO>){
        bestActorAdapter.setNewData(actors)
    }
}