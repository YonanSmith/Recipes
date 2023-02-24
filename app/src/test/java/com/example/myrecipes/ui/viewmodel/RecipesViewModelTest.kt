package com.example.myrecipes.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myrecipes.domain.model.Autor
import com.example.myrecipes.domain.model.Recipe
import com.example.myrecipes.domain.usecase.GetRecipesUseCase
import com.example.myrecipes.ui.view.viewmodel.RecipesViewModel
import com.example.myrecipes.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class RecipesViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()

    @RelaxedMockK
    private lateinit var getRecipesUseCase: GetRecipesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when view-model is created at the first time get all recipes`() = runTest {
        val recipes = listOf(
            Recipe(
                id = 1,
                nombre = "Nombre 1",
                imagenes = emptyList(),
                descripcion = "Descripcion 1",
                dificultad = "Dificultad 1",
                duracion = "Duracion 1",
                ingredientes = emptyList(),
                preparacion = "Preparacion 1",
                autor = Autor(
                    nombre = "Nombre 1",
                    edad = "Edad 1",
                    nacionalidad = "Nacionalidad 1",
                    latitud = -1.1,
                    longitud = -1.1
                )
            ),
            Recipe(
                id = 2,
                nombre = "Nombre 2",
                imagenes = emptyList(),
                descripcion = "Descripcion 2",
                dificultad = "Dificultad 2",
                duracion = "Duracion 2",
                ingredientes = emptyList(),
                preparacion = "Preparacion 2",
                autor = Autor(
                    nombre = "Nombre 2",
                    edad = "Edad 2",
                    nacionalidad = "Nacionalidad 2",
                    latitud = -2.2,
                    longitud = -2.2
                )
            )
        )
        coEvery { getRecipesUseCase() } returns(recipes)
        val recipesViewModel = RecipesViewModel(getRecipesUseCase)
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(recipesViewModel.recipesState.value?.data, recipes)
    }

    @Test
    fun `when view-model is created at the first time validate loading status and success`() = runTest {
        val state = mutableListOf<Resource<List<Recipe>>>()
        val recipesViewModel = RecipesViewModel(getRecipesUseCase)
        recipesViewModel.recipesState.observeForever {
            state.add(it)
        }
        coEvery { getRecipesUseCase() } returns emptyList()
        dispatcher.scheduler.advanceUntilIdle()
        assert(state[0] is Resource.Loading)
        assert(state[1] is Resource.Success)
    }

}