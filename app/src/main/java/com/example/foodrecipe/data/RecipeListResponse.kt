package com.example.foodrecipe.data


data class RecipeListResponse(
    val count: Int,
    val results: List<Results>
)