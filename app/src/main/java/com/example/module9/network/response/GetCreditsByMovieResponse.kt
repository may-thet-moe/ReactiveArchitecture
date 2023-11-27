package com.example.module9.network.response

import com.example.module9.data.vos.ActorVO
import com.google.gson.annotations.SerializedName

data class GetCreditsByMovieResponse(
    @SerializedName("cast")
    val cast : List<ActorVO>?,

    @SerializedName("crew")
    val crew : List<ActorVO>?
)
