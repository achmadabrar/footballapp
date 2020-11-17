package com.achmadabrar.myapplication.data.database.converters

import androidx.room.TypeConverter
import com.achmadabrar.myapplication.data.models.League
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LeagueConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<League>): String {
            val type = object : TypeToken<MutableList<League>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<League> {
            val type = object : TypeToken<MutableList<League>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}