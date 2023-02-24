package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.database.entity.AutorEntity
import com.example.myrecipes.data.database.entity.toDatabase
import com.example.myrecipes.data.repository.recipe.RecipesRepository
import com.example.myrecipes.domain.model.Recipe
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: RecipesRepository) {

    suspend operator fun invoke(): List<Recipe> {
        return try {
            val recipes = repository.getAllRecipes()
            if (recipes.isNotEmpty()) insertRecipes(recipes, insertAuthors(recipes))
            recipes
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    private suspend fun insertAuthors(recipes: List<Recipe>): List<Long> {
        repository.clearAuthors()
        val authors = mutableListOf<AutorEntity>()
        recipes.map {
            authors.add(it.autor.toDatabase())
        }
        return repository.insertAuthor(authors)
    }

    private suspend fun insertRecipes(recipes: List<Recipe>, authorsId: List<Long>) {
        repository.clearRecipes()
        val recipesEntity = recipes.map { it.toDatabase() }
        var i = 0
        recipesEntity.map {
            it.authorId = authorsId[i].toInt()
            i++
        }
        repository.insertRecipes(recipesEntity)
    }
}