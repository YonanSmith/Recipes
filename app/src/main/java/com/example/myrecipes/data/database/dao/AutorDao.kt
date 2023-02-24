package com.example.myrecipes.data.database.dao

import androidx.room.*
import com.example.myrecipes.data.database.entity.AutorEntity
import com.example.myrecipes.data.database.entity.RecipeEntity
import com.example.myrecipes.data.database.entity.RecipeWithAutor

@Dao
interface AutorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(autors: List<AutorEntity>) : List<Long>

    @Query("DELETE FROM autor")
    suspend fun deleteAutors()

    @Transaction
    @Query("SELECT * FROM autor")
    suspend fun getAllWithAutor(): List<RecipeWithAutor>
}