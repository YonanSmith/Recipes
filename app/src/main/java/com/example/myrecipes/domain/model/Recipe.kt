package com.example.myrecipes.domain.model

import android.os.Parcelable
import com.example.myrecipes.data.database.entity.RecipeWithAutor
import com.example.myrecipes.data.model.RecipeModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: Int,
    val nombre: String,
    val imagenes: List<String>,
    val descripcion: String,
    val dificultad: String,
    val duracion: String,
    val ingredientes: List<String>,
    val preparacion: String,
    val autor: Autor
) : Parcelable

fun RecipeModel.toDomain() = Recipe(
    id,
    nombre,
    imagenes,
    descripcion,
    dificultad,
    duracion,
    ingredientes,
    preparacion,
    autor = Autor(autor.nombre, autor.edad, autor.nacionalidad, autor.latitud, autor.longitud)
)
fun RecipeWithAutor.toDomain() = Recipe(
    id = recipe.id,
    nombre = recipe.nombre,
    imagenes = recipe.imagenes,
    descripcion = recipe.descripcion,
    dificultad = recipe.dificultad,
    duracion = recipe.duracion,
    ingredientes = recipe.ingredientes,
    preparacion = recipe.preparacion,
    autor = Autor(
        nombre = autor.nombre,
        edad = autor.edad,
        nacionalidad = autor.nacionalidad,
        latitud = autor.latitud,
        longitud = autor.longitud
    )
)