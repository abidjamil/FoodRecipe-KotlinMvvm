package com.example.foodrecipe.ui.recipedetail

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodrecipe.api.Result
import com.example.foodrecipe.base.BaseViewModel
import com.example.foodrecipe.data.RecipeListResponse
import com.example.foodrecipe.repository.RecipeRepository
import kotlinx.coroutines.Job

class RecipeDetailViewModel(private val recipeRepository: RecipeRepository) : BaseViewModel() {
    var job = Job()
    private lateinit var _recipeSimilarListingLiveData: MutableLiveData<Result<RecipeListResponse>>
    private var recipeSimilarListResultLiveData: MediatorLiveData<Result<RecipeListResponse>> =
        MediatorLiveData()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun getSimilarRecipeList() = recipeSimilarListResultLiveData

    fun fetchSimilarRecipeList(recipeId: Int) {
        val data: MutableMap<String, String> = HashMap()
        data["recipe_id"] = recipeId.toString()
        if (this::_recipeSimilarListingLiveData.isInitialized)
            recipeSimilarListResultLiveData.removeSource(_recipeSimilarListingLiveData)

        _recipeSimilarListingLiveData = recipeRepository.getASimilarRecipeList(data, job)
        recipeSimilarListResultLiveData.addSource(_recipeSimilarListingLiveData) { outcome ->
            Log.d("responseList", outcome.toString())
            // on the base of outcome loader visible and gone on base activity
            outcomeLiveData.value = outcome
            outcome.let {
                recipeSimilarListResultLiveData.value = it
            }

            when (outcome) {
                is Result.Success -> {
                    outcome.let {
                        recipeSimilarListResultLiveData.value = it
                    }
                }
                else -> {

                }
            }
        }


    }

}