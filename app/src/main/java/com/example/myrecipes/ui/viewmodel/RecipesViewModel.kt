package com.example.myrecipes.ui.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myrecipes.domain.model.Recipe
import com.example.myrecipes.domain.usecase.GetRecipesUseCase
import com.example.myrecipes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

    val recipesState = MutableLiveData<Resource<List<Recipe>>>()

    init {
        getAllRecipes()
    }

    fun getAllRecipes() {
        viewModelScope.launch {
            runCatching {
                recipesState.postValue(Resource.Loading(true))
                getRecipesUseCase()
            }.onSuccess { recipes ->
                recipesState.postValue(Resource.Success(recipes))
            }.onFailure { error ->
                recipesState.postValue(Resource.Error(error.message.toString()))
            }
        }
    }
}