package com.space.movieapp.utils

import androidx.room.TypeConverter

class ListTypeConverters {

    @TypeConverter
    fun toStringList(data: String): List<String> {
        return data.split(",")
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return list.joinToString(",")
    }
}