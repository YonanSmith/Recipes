package com.example.myrecipes.data.model

import com.google.gson.annotations.SerializedName

data class AutorModel(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("edad")
    val edad: String,
    @SerializedName("nacionalidad")
    val nacionalidad: String,
    @SerializedName("latitud")
    val latitud: Double,
    @SerializedName("longitud")
    val longitud: Double
)