package com.example.foodrecipe.ui.recipelisting

import com.example.foodrecipe.R
import com.example.foodrecipe.base.BaseAdapter
import com.example.foodrecipe.base.BaseViewHolder
import com.example.foodrecipe.data.Results
import com.example.foodrecipe.databinding.RecipeListRowBinding

class RecipeListAdapter(private val clickListener: (results: Results, clickOnPlay: Boolean) -> Unit) :
    BaseAdapter<RecipeListRowBinding>() {
    init {
        addData(ArrayList<Results>())
    }

    fun addRecipeListData(data: List<Results>) {
        data?.let {
            addData(it)
            notifyDataSetChanged()
        }
    }

    override fun viewHolder(
        layout: Int,
        binding: RecipeListRowBinding
    ): BaseViewHolder<RecipeListRowBinding> {
        return RecipeListViewHolder(binding, clickListener)
    }

    override fun layout(position: Int): Int {
        return R.layout.recipe_list_row
    }
}