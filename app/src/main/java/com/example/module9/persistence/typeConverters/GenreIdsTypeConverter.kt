package com.example.module9.persistence.typeConverters

import androidx.room.TypeConverter
import com.example.module9.data.vos.GenreVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverter {

    @TypeConverter
    fun toString(genreIdsList : List<Int>?) : String{
        return Gson().toJson(genreIdsList)
    }

    @TypeConverter
    fun toCollectionVO(genreIdsListJsonStr : String ): List<Int>? {
        val genreIdsListType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreIdsListJsonStr, genreIdsListType)
    }
}