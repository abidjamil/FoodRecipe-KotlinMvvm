package com.example.foodrecipe.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.foodrecipe.base.BaseAdapter
import com.example.foodrecipe.data.RecipeListResponse
import com.example.foodrecipe.api.Result
import com.example.foodrecipe.ui.recipelisting.RecipeListAdapter
import com.example.foodrecipe.ui.recipelisting.RecipeViewModel
import com.example.foodrecipe.util.Paginator

@BindingAdapter("adapter")
fun bindRecyclerViewAdapter(recyclerView: RecyclerView, adapter: BaseAdapter<*>) {
    recyclerView.adapter = recyclerView.adapter ?: adapter
}


@BindingAdapter("payload")
fun bindRecyclerData(recyclerView: RecyclerView, response: Result<RecipeListResponse>?) {
    response?.let {
        if (it is Result.Success) {
            it.data.let { recipeResponse ->
                if (recipeResponse.results == null) {
                    return
                }
                val adapter = recyclerView.adapter as? RecipeListAdapter
                adapter?.totalCount = recipeResponse.count
                adapter?.addRecipeListData(recipeResponse.results)
            }
        }
    }

}

@BindingAdapter("recipeListPagination")
fun bindNewPagination(recyclerView: RecyclerView, ViewModel: RecipeViewModel) {
    Paginator(
        recyclerView,
        isLoading = {
            return@Paginator ViewModel.outcomeLiveData.value is Result.Loading
        },
        loadMore = { ViewModel.fetchAllRecipeList(it) }
    ).run {
        currentPage = 0
    }
}