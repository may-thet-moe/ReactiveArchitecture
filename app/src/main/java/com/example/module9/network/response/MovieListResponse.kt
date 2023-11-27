package com.example.module9.network.response

import com.example.module9.data.vos.DateVO
import com.example.module9.data.vos.MovieVO
import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("dates")
    val dates : DateVO?,

    @SerializedName("page")
    val page : Int?,

    @SerializedName("results")
    val results : List<MovieVO>?

)
