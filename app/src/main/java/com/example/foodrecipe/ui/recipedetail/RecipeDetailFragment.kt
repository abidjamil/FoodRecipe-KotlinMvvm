package com.example.foodrecipe


import com.example.foodrecipe.base.BaseFragment
import com.example.foodrecipe.base.BaseViewModel
import com.example.foodrecipe.databinding.FragmentRecipeDetailBinding
import com.example.foodrecipe.ui.recipedetail.RecipeDetailViewModel
import com.example.foodrecipe.ui.recipelisting.RecipeListAdapter
import com.example.foodrecipe.util.VideoUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeDetailFragment : BaseFragment<FragmentRecipeDetailBinding>() {
    val recipeResult by lazy {
        RecipeDetailFragmentArgs.fromBundle(requireArguments()).recipeResult
    }
    private val viewModel by viewModel<RecipeDetailViewModel>()
    private val recipeAdapter by lazy {
        RecipeListAdapter { recipeResult, clickOnPlay ->
            if (clickOnPlay) {
                context?.let { VideoUtils.playVideo(recipeResult.video_url, it) }
            }

        }
    }

    override fun getViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun initFragment() {
        if (binding.lifecycleOwner == null) {
            binding.apply {
                lifecycleOwner = this@RecipeDetailFragment
                data = this@RecipeDetailFragment.recipeResult
                viewModel = this@RecipeDetailFragment.viewModel
                adapter = this@RecipeDetailFragment.recipeAdapter
            }
        }
        viewModel.fetchSimilarRecipeList(recipeResult.id)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_recipe_detail
    }

}