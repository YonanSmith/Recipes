package com.example.myrecipes.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Autor(
    val nombre: String,
    val edad: String,
    val nacionalidad: String,
    val latitud: Double,
    val longitud: Double
) : Parcelable