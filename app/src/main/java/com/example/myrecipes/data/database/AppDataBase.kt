package com.example.myrecipes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myrecipes.data.database.dao.AutorDao
import com.example.myrecipes.data.database.dao.RecipeDao
import com.example.myrecipes.data.database.entity.AutorEntity
import com.example.myrecipes.data.database.entity.RecipeEntity
import com.example.myrecipes.util.Converters

@Database(
    entities = [
        RecipeEntity::class,
        AutorEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun autorDao(): AutorDao

}