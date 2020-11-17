package com.achmadabrar.myapplication.data.database.converters

import androidx.room.TypeConverter
import java.sql.Timestamp
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) Date(Timestamp(System.currentTimeMillis()).time) else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}