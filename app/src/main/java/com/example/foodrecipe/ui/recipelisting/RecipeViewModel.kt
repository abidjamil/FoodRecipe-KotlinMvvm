package com.example.foodrecipe.ui.recipelisting

import android.provider.SyncStateContract
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodrecipe.api.Result
import com.example.foodrecipe.base.BaseViewModel
import com.example.foodrecipe.constants.pageSize
import com.example.foodrecipe.data.RecipeListResponse
import com.example.foodrecipe.repository.RecipeRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

class RecipeViewModel(private val recipeRepository: RecipeRepository) : BaseViewModel() {
    var job = Job()
    private lateinit var _recipeListingLiveData: MutableLiveData<Result<RecipeListResponse>>
     var query: MutableLiveData<String> = MutableLiveData("")
    private var recipeListResultLiveData: MediatorLiveData<Result<RecipeListResponse>> =
        MediatorLiveData()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun getAllRecipeList() = recipeListResultLiveData

    fun fetchAllRecipeList(page: Int) {
        if (query.value.isNullOrEmpty()) {
            return
        }
        val data: MutableMap<String, String> = HashMap()
        data["from"] = page.toString()
        data["size"] = pageSize.toString()
        data["q"] = query.value.toString()
        if (this::_recipeListingLiveData.isInitialized)
            recipeListResultLiveData.removeSource(_recipeListingLiveData)

        _recipeListingLiveData = recipeRepository.getAllRecipeList(data, job)
        recipeListResultLiveData.addSource(_recipeListingLiveData) { outcome ->
            Log.d("responseList", outcome.toString())
            // on the base of outcome loader visible and gone on base activity
            outcomeLiveData.value = outcome
            outcome.let {
                recipeListResultLiveData.value = it
            }

            when (outcome) {
                is Result.Success -> {
                    outcome.let {
                        recipeListResultLiveData.value = it
                    }
                }
                else -> {

                }
            }
        }
    }

    fun onSubmit() {
        fetchAllRecipeList(0)
    }
}