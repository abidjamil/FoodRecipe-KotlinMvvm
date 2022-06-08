package com.example.foodrecipe.repository

import androidx.lifecycle.MutableLiveData
import com.example.foodrecipe.api.Result
import com.example.foodrecipe.data.RecipeListResponse
import kotlinx.coroutines.Job

interface RecipeRepository {
    fun getAllRecipeList(
        query: Map<String, String>,
        job: Job
    ): MutableLiveData<Result<RecipeListResponse>>

    fun getASimilarRecipeList(
        query: Map<String, String>,
        job: Job
    ): MutableLiveData<Result<RecipeListResponse>>
}