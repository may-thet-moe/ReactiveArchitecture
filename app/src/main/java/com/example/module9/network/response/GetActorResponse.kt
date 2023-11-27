package com.example.module9.network.response

import com.example.module9.data.vos.ActorVO
import com.google.gson.annotations.SerializedName

data class GetActorResponse(
    @SerializedName("results")
    val results : List<ActorVO>
)
