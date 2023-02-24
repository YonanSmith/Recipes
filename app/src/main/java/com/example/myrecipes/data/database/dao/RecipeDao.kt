package com.example.myrecipes.data.database.dao

import androidx.room.*
import com.example.myrecipes.data.database.entity.RecipeEntity
import com.example.myrecipes.data.database.entity.RecipeWithAutor

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipes(): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(recipes: List<RecipeEntity>)

    @Query("DELETE FROM recipe")
    suspend fun deleteRecipes()
}