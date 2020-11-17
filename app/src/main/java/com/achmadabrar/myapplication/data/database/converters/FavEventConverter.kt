package com.achmadabrar.myapplication.data.database.converters

import androidx.room.TypeConverter
import com.achmadabrar.myapplication.data.models.FavModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavEventConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<FavModel>): String {
            val type = object : TypeToken<MutableList<FavModel>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<FavModel> {
            val type = object : TypeToken<MutableList<FavModel>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}