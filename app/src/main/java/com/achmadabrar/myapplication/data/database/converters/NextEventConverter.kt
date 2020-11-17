package com.achmadabrar.myapplication.data.database.converters

import androidx.room.TypeConverter
import com.achmadabrar.myapplication.data.database.table.NextEvent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NextEventConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<NextEvent>): String {
            val type = object : TypeToken<MutableList<NextEvent>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<NextEvent> {
            val type = object : TypeToken<MutableList<NextEvent>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}