package com.example.foodrecipe.di

import com.example.foodrecipe.api.RecipeApi
import com.example.foodrecipe.repository.RecipeRepository
import com.example.foodrecipe.repository.implementation.RecipeRepositoryImp
import com.example.foodrecipe.ui.recipedetail.RecipeDetailViewModel
import com.example.foodrecipe.ui.recipelisting.RecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { createRecipeRepository(get()) }
}

val viewModelModule = module {
    viewModel {
        RecipeViewModel(get())
    }
    viewModel {
        RecipeDetailViewModel(get())
    }


}

fun createRecipeRepository(recipeApi: RecipeApi): RecipeRepository {
    return RecipeRepositoryImp(recipeApi)
}