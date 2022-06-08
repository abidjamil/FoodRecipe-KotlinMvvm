package com.example.foodrecipe.ui.recipelisting

import android.view.View
import com.example.foodrecipe.base.BaseViewHolder
import com.example.foodrecipe.data.Results
import com.example.foodrecipe.databinding.RecipeListRowBinding

class RecipeListViewHolder(
    view: RecipeListRowBinding,
    private val onClickListener: (results: Results, clickOnPlay:Boolean) -> Unit
) : BaseViewHolder<RecipeListRowBinding>(view) {
    private lateinit var results: Results
    override fun bindData(data: Any, position: Int) {
        if (data is Results) {
            results = data
            binding().data = results
        }
        binding().ivPlayIcon.setOnClickListener {
            onClickListener(results,true)
        }
    }

    override fun onClick(p0: View?) {
        onClickListener(results,false)
    }

    override fun onLongClick(p0: View?): Boolean {
        return false
    }
}