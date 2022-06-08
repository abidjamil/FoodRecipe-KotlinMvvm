package com.example.foodrecipe.repository.implementation

import androidx.lifecycle.MutableLiveData
import com.example.foodrecipe.api.RecipeApi
import com.example.foodrecipe.api.Result
import com.example.foodrecipe.base.BaseRepository
import com.example.foodrecipe.data.RecipeListResponse
import com.example.foodrecipe.repository.RecipeRepository
import kotlinx.coroutines.Job

class RecipeRepositoryImp(private val recipeApi: RecipeApi): RecipeRepository {
    override fun getAllRecipeList(
        query: Map<String, String>,
        job: Job
    ): MutableLiveData<Result<RecipeListResponse>> {
        return object : BaseRepository<RecipeListResponse,Map<String,String>>(){
            override suspend fun fetchFromNetwork(params: Map<String, String>): RecipeListResponse {
                return recipeApi.getAllRecipeList(params)
            }

        }.start(query,job) as MutableLiveData<Result<RecipeListResponse>>
    }

    override fun getASimilarRecipeList(
        query: Map<String, String>,
        job: Job
    ): MutableLiveData<Result<RecipeListResponse>> {
        return object : BaseRepository<RecipeListResponse,Map<String,String>>(){
            override suspend fun fetchFromNetwork(params: Map<String, String>): RecipeListResponse {
                return recipeApi.getSimilarRecipeList(params)
            }
        }.start(query,job) as MutableLiveData<Result<RecipeListResponse>>
    }
}