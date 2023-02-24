package com.example.myrecipes.data.network.recipe

import com.example.myrecipes.data.model.RecipeModel
import com.example.myrecipes.data.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipeService @Inject constructor(private val api: ApiClient) {

    suspend fun getAllRecipes(): List<RecipeModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllRecipes()
            response.body() ?: emptyList()
        }
    }
}