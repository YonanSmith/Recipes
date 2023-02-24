package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.repository.recipe.RecipesRepository
import com.example.myrecipes.domain.model.Autor
import com.example.myrecipes.domain.model.Recipe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetRecipesUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: RecipesRepository

    lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRecipesUseCase = GetRecipesUseCase(repository)
    }

    @Test
    fun `when the api returns nothing not cleaning and saving data to database`() = runBlocking {
        // Given
        coEvery { repository.getAllRecipes() } returns emptyList()

        // When
        getRecipesUseCase()

        // Then
        coVerify(exactly = 0) { repository.clearAuthors() }
        coVerify(exactly = 0) { repository.insertAuthor(emptyList()) }
        coVerify(exactly = 0) { repository.clearRecipes() }
        coVerify(exactly = 0) { repository.insertRecipes(emptyList()) }
    }

}