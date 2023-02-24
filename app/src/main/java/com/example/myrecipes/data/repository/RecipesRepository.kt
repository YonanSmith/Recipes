package com.example.myrecipes.data.repository.recipe

import com.example.myrecipes.data.database.dao.AutorDao
import com.example.myrecipes.data.database.dao.RecipeDao
import com.example.myrecipes.data.database.entity.AutorEntity
import com.example.myrecipes.data.database.entity.RecipeEntity
import com.example.myrecipes.data.network.recipe.RecipeService
import com.example.myrecipes.domain.model.Recipe
import com.example.myrecipes.domain.model.toDomain
import com.example.myrecipes.util.Network
import javax.inject.Inject

class RecipesRepository @Inject constructor(
    private val api: RecipeService,
    private val recipeDao: RecipeDao,
    private val authorDao: AutorDao,
    private val network: Network
) {

    suspend fun getAllRecipes(): List<Recipe> {
        return try {
            if (network.isConnected()) {
                val response = api.getAllRecipes()
                response.map {
                    it.toDomain()
                }
            } else {
                val response = authorDao.getAllWithAutor()
                response.map {
                    it.toDomain()
                }
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    suspend fun insertRecipes(recipes: List<RecipeEntity>) {
        recipeDao.insertMultiple(recipes)
    }

    suspend fun insertAuthor(authors: List<AutorEntity>): List<Long> {
        return authorDao.insertMultiple(authors)
    }

    suspend fun clearRecipes() {
        recipeDao.deleteRecipes()
    }

    suspend fun clearAuthors() {
        authorDao.deleteAutors()
    }
}