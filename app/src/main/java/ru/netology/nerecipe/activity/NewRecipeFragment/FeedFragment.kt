package ru.netology.nerecipe.activity.NewRecipeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.authorArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.categoryArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.textArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.titleArg
import ru.netology.nerecipe.activity.NewRecipeFragment.OneRecipeFragment.Companion.textId
import ru.netology.nerecipe.activity.R
import ru.netology.nerecipe.activity.adapter.RecipeAdapter
import ru.netology.nerecipe.activity.viewModel.RecipeViewModel
import ru.netology.nerecipe.activity.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)

        val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

        val adapter = RecipeAdapter(
            viewModel
        )

        binding.recipesRecyclerView.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { recipes ->
            adapter.submitList(recipes)
        }

        binding.addrecipeButton.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newRecipeFragment)
        }

        viewModel.editEvent.observe(viewLifecycleOwner) { currentRecipe ->
            findNavController().navigate(R.id.action_feedFragment_to_newRecipeFragment,
                Bundle().apply {
                    categoryArg = currentRecipe?.category
                    authorArg = currentRecipe?.author
                    titleArg = currentRecipe?.title
                    textArg = currentRecipe?.stepsRecipe
                })
        }

        viewModel.openRecipeEvent.observe(viewLifecycleOwner) { openRecipeEvent ->
            if (openRecipeEvent != null) {
                findNavController().navigate(
                    R.id.action_feedFragment_to_oneRecipeFragment,
                    Bundle().apply {
                        textId = openRecipeEvent.id.toString()
                    })
            }
        }

        binding.favoriterecipeButton.setOnClickListener{
            viewModel.filteredRecipe.observe(viewLifecycleOwner){ currentRecipe ->
                viewModel.onFavoriteClicked(currentRecipe!!)
            }
        }

        return binding.root
    }
}