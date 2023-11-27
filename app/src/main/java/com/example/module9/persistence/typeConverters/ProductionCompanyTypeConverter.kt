package com.example.module9.persistence.typeConverters

import androidx.room.TypeConverter
import com.example.module9.data.vos.GenreVO
import com.example.module9.data.vos.ProductionCompaniesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCompanyTypeConverter {
    @TypeConverter
    fun toString(productionCompaniesList : List<ProductionCompaniesVO>?) : String{
        return Gson().toJson(productionCompaniesList)
    }

    @TypeConverter
    fun toCollectionVO(productionCompanyListStr : String ): List<ProductionCompaniesVO>? {
        val productionCompanyListType = object : TypeToken<List<ProductionCompaniesVO>?>() {}.type
        return Gson().fromJson(productionCompanyListStr, productionCompanyListType)
    }
}