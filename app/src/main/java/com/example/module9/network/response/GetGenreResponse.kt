package com.example.module9.network.response

import com.example.module9.data.vos.GenreVO
import com.google.gson.annotations.SerializedName

data class GetGenreResponse(
    @SerializedName("genres")
    val genres : List<GenreVO>
)
