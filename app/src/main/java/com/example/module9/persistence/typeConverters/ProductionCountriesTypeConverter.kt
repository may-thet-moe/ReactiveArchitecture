package com.example.module9.persistence.typeConverters

import androidx.room.TypeConverter
import com.example.module9.data.vos.ProductionCompaniesVO
import com.example.module9.data.vos.ProductionCountriesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCountriesTypeConverter {
    @TypeConverter
    fun toString(productionCountryList : List<ProductionCountriesVO>?) : String{
        return Gson().toJson(productionCountryList)
    }

    @TypeConverter
    fun toCollectionVO(productionCountryListStr : String ): List<ProductionCountriesVO>? {
        val productionCountryListType = object : TypeToken<List<ProductionCountriesVO>?>() {}.type
        return Gson().fromJson(productionCountryListStr, productionCountryListType)
    }
}