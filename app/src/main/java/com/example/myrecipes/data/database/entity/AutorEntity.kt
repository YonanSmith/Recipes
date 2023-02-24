package com.example.myrecipes.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myrecipes.domain.model.Autor

@Entity(tableName = "autor")
data class AutorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "nombre") val nombre: String,
    @ColumnInfo(name = "edad") val edad: String,
    @ColumnInfo(name = "nacionalidad") val nacionalidad: String,
    @ColumnInfo(name = "latitud") val latitud: Double,
    @ColumnInfo(name = "longitud") val longitud: Double
)

fun Autor.toDatabase() = AutorEntity(
    nombre = nombre,
    edad = edad,
    nacionalidad = nacionalidad,
    latitud = latitud,
    longitud = longitud
)