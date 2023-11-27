package com.example.module9.persistence.typeConverters

import androidx.room.TypeConverter
import com.example.module9.data.vos.CollectionVO
import com.example.module9.data.vos.GenreVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreListTypeConveter {
    @TypeConverter
    fun toString(genreList : List<GenreVO>?) : String{
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toCollectionVO(genreListJsonStr : String ): List<GenreVO>? {
        val genreListType = object : TypeToken<List<GenreVO>?>() {}.type
        return Gson().fromJson(genreListJsonStr, genreListType)
    }
}