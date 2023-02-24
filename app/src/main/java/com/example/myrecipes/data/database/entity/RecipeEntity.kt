package com.example.myrecipes.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.myrecipes.domain.model.Recipe

@Entity(tableName = "recipe", indices = [Index(value = ["nombre"], unique = true)])
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "imagenes") val imagenes: ArrayList<String>,
    @ColumnInfo(name = "descripcion") val descripcion: String,
    @ColumnInfo(name = "dificultad") val dificultad: String,
    @ColumnInfo(name = "duracion") val duracion: String,
    @ColumnInfo(name = "ingredientes") val ingredientes: ArrayList<String>,
    @ColumnInfo(name = "preparacion") val preparacion: String,
    @ColumnInfo(name = "authorId") var authorId: Int? = 0
)

fun Recipe.toDatabase() = RecipeEntity(
    id,
    nombre,
    imagenes as ArrayList<String>,
    descripcion,
    dificultad,
    duracion,
    ingredientes as ArrayList<String>,
    preparacion
)