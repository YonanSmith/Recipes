package com.example.myrecipes.data.model

import com.google.gson.annotations.SerializedName

data class RecipeModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("imagenes")
    val imagenes: List<String>,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("dificultad")
    val dificultad: String,
    @SerializedName("duracion")
    val duracion: String,
    @SerializedName("ingredientes")
    val ingredientes: List<String>,
    @SerializedName("preparacion")
    val preparacion: String,
    @SerializedName("autor")
    val autor: AutorModel
)