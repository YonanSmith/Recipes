package com.example.myrecipes.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithAutor(
    @Embedded val autor: AutorEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "authorId"
    )
    val recipe: RecipeEntity
)