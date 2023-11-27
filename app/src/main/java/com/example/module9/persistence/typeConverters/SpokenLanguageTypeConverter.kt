package com.example.module9.persistence.typeConverters

import androidx.room.TypeConverter
import com.example.module9.data.vos.GenreVO
import com.example.module9.data.vos.SpokenLanguagesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguagesList : List<SpokenLanguagesVO>?) : String{
        return Gson().toJson(spokenLanguagesList)
    }

    @TypeConverter
    fun toCollectionVO(spokenLanguageListStr : String ): List<SpokenLanguagesVO>? {
        val spokenLanguagesListType  = object : TypeToken<List<SpokenLanguagesVO>?>() {}.type
        return Gson().fromJson(spokenLanguageListStr, spokenLanguagesListType)
    }
}