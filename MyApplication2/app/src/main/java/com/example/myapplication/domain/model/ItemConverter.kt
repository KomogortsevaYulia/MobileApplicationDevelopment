package com.example.myapplication.domain.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemConverter {

    @TypeConverter
    fun fromItem(items: List<Item>): String {
        val type = object: TypeToken<List<Item>>() {}.type
        return Gson().toJson(items, type)
    }

    @TypeConverter
    fun toItem(itemString: String): List<Item> {
        val type = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(itemString, type)
    }
}