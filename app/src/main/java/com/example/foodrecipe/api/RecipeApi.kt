package com.example.foodrecipe.api

import com.example.foodrecipe.data.RecipeListResponse
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RecipeApi {
    @GET("recipes/list")
    suspend fun getAllRecipeList(@QueryMap params: Map<String, String>)
    : RecipeListResponse

    @GET("recipes/list-similarities")
    suspend fun getSimilarRecipeList(@QueryMap params: Map<String, String>)
            : RecipeListResponse
}