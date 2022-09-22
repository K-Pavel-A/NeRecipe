package ru.netology.nerecipe.activity.NewRecipeFragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.map
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.authorArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.categoryArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.textArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.titleArg
import ru.netology.nerecipe.activity.R
import ru.netology.nerecipe.activity.adapter.RecipeAdapter
import ru.netology.nerecipe.activity.databinding.FragmentFilteredRecipeBinding
import ru.netology.nerecipe.activity.viewModel.RecipeViewModel

class FilteredRecipeFragment: Fragment() {
    private val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFilteredRecipeBinding.inflate(inflater, container, false)

        val adapter = RecipeAdapter(
            viewModel
        )

        binding.filteredRecipe.cancelButton.visibility = View.VISIBLE

        binding.filteredRecipe.cancelButton.setOnClickListener{
            viewModel.clearFilter()
            findNavController().navigate(R.id.action_filteredRecipeFragment_to_feedFragment)
        }

        binding.filteredRecipe.recipesRecyclerView.adapter = adapter

        viewModel.data.observe(viewLifecycleOwner) { recipes ->
            adapter.submitList(recipes)
        }

        binding.filteredRecipe.filterButton.setOnClickListener{
            viewModel.clearFilter()
            findNavController().navigate(R.id.action_filteredRecipeFragment_to_filtersFragment)
        }

        binding.filteredRecipe.feedButton.setOnClickListener{
            viewModel.clearFilter()
            findNavController().navigate(R.id.action_filteredRecipeFragment_to_feedFragment)
        }

        binding.filteredRecipe.favoriterecipeButton.setOnClickListener{
            viewModel.clearFilter()
            findNavController().navigate(R.id.action_filteredRecipeFragment_to_favoriteRecipeFragment)
        }

        binding.filteredRecipe.addrecipeButton.setOnClickListener{
            viewModel.clearFilter()
            findNavController().navigate(R.id.action_filteredRecipeFragment_to_newRecipeFragment)
        }

        binding.filteredRecipe.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotBlank()) {
                    viewModel.onSearchClicked(newText)
                    viewModel.data.observe(viewLifecycleOwner) { recipe ->
                        adapter.submitList(recipe)
                    }
                }
                if (TextUtils.isEmpty(newText)){
                    viewModel.clearFilter()
                    viewModel.data.observe(viewLifecycleOwner) { recipe ->
                        adapter.submitList(recipe)
                    }
                }
                return false
            }
        })

        viewModel.editEvent.observe(viewLifecycleOwner) { currentRecipe ->
            findNavController().navigate(R.id.action_filteredRecipeFragment_to_newRecipeFragment,
                Bundle().apply {
                    categoryArg = currentRecipe?.category
                    authorArg = currentRecipe?.author
                    titleArg = currentRecipe?.title
                    textArg = currentRecipe?.stepsRecipe
                })
        }

        return binding.root
    }

}