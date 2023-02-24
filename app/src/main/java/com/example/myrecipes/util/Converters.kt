package com.example.myrecipes.util

import androidx.room.TypeConverter
import com.example.myrecipes.data.database.entity.AutorEntity
import com.google.gson.Gson
class Converters {
//    @TypeConverter
//    fun fromStringAutoEntity(value: AutorEntity): String {
//        return Gson().toJson(value)
//    }
//
//    @TypeConverter
//    fun toStringAutoEntity(value: String): AutorEntity {
//        return try {
//            Gson().fromJson(value) //using extension function
//        } catch (e: Exception) {
//            AutorEntity(0, "", "", "", -0.0, -0.0)
//        }
//    }
    @TypeConverter
    fun fromStringArrayList(value: ArrayList<String>): String {
        return Gson().toJson(value)
    }
    @TypeConverter
    fun toStringArrayList(value: String): ArrayList<String> {
        return try {
            Gson().fromJson(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }

}