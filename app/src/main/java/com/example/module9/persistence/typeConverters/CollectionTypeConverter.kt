package com.example.module9.persistence.typeConverters

import androidx.room.TypeConverter
import com.example.module9.data.vos.CollectionVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CollectionTypeConverter {
    @TypeConverter
    fun toString(collection : CollectionVO?) : String{
        return Gson().toJson(collection)
    }

    @TypeConverter
    fun toCollectionVO(commentListJsonStr : String ): CollectionVO? {
        val collectionVOType = object : TypeToken<CollectionVO?>() {}.type
        return Gson().fromJson(commentListJsonStr, collectionVOType)
    }
}