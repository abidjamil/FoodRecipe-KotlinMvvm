package com.example.foodrecipe

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.foodrecipe.base.BaseFragment
import com.example.foodrecipe.base.BaseViewModel
import com.example.foodrecipe.databinding.FragmentRecipeDetailBinding
import com.example.foodrecipe.databinding.FragmentRecipeListBinding
import com.example.foodrecipe.ui.recipelisting.RecipeListAdapter
import com.example.foodrecipe.ui.recipelisting.RecipeViewModel
import com.example.foodrecipe.util.VideoUtils
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipeListFragment : BaseFragment<FragmentRecipeListBinding>() {

    private val viewModel by viewModel<RecipeViewModel>()

    private val adapterRecipe by lazy {
        RecipeListAdapter { result, clickOnPlay ->
            if (clickOnPlay) {
                context?.let { VideoUtils.playVideo(result.video_url, it) }
            } else {
                val bundle = Bundle()
                bundle.putParcelable("recipeResult", result)
                val args = RecipeDetailFragmentArgs.fromBundle(bundle)
                navigateToDestination(R.id.actionRecipeListToDetails, args.toBundle())

            }

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_recipe_list
    }


    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun initFragment() {
        if (binding.lifecycleOwner == null) {
            binding.apply {
                lifecycleOwner = this@RecipeListFragment
                viewModel = this@RecipeListFragment.viewModel
                adapter = this@RecipeListFragment.adapterRecipe

            }
        }
        initObserver()
    }

    fun initObserver() {
        viewModel.query.observe(this, Observer {
            binding.adapter?.clearData()
            binding.adapter?.notifyDataSetChanged()
        })

    }
}