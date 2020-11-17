package com.achmadabrar.myapplication.data.database.converters

import androidx.room.TypeConverter
import com.achmadabrar.myapplication.data.database.table.PrevEvent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PrevEventConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun mutableListToString(string: MutableList<PrevEvent>): String {
            val type = object : TypeToken<MutableList<PrevEvent>>() {}.type
            return Gson().toJson(string, type)
        }

        @TypeConverter
        @JvmStatic
        fun stringToMutableList(string: String): MutableList<PrevEvent> {
            val type = object : TypeToken<MutableList<PrevEvent>>() {}.type
            return Gson().fromJson(string, type)
        }
    }
}