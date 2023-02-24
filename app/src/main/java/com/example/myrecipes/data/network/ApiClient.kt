package com.example.myrecipes.data.network

import com.example.myrecipes.data.model.RecipeModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("recipes")
    suspend fun getAllRecipes(): Response<List<RecipeModel>>

}