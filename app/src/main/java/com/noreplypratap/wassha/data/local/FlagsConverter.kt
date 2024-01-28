package com.noreplypratap.wassha.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.noreplypratap.wassha.model.Flags

class FlagsConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromAny(flags: Flags): String{
        return gson.toJson(flags)
    }

    @TypeConverter
    fun toAny(data: String): Flags {
        val listType = object : TypeToken<Flags>() {}.type
        return gson.fromJson(data, listType)
    }

}