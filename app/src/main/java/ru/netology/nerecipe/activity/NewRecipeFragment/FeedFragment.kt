package ru.netology.nerecipe.activity.NewRecipeFragment

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.authorArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.categoryArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.step1Arg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.step2Arg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.step3Arg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.step4Arg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.step5Arg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.titleArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.uriArg
import ru.netology.nerecipe.activity.NewRecipeFragment.OneRecipeFragment.Companion.textId
import ru.netology.nerecipe.activity.R
import ru.netology.nerecipe.activity.adapter.RecipeAdapter
import ru.netology.nerecipe.activity.viewModel.RecipeViewModel
import ru.netology.nerecipe.activity.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

    val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)

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
                    step1Arg = currentRecipe?.step1Recipe
                    uriArg = currentRecipe?.pictureUri
                    step2Arg = currentRecipe?.step2Recipe
                    step3Arg = currentRecipe?.step3Recipe
                    step4Arg = currentRecipe?.step4Recipe
                    step5Arg = currentRecipe?.step5Recipe
                })
        }

        viewModel.openRecipeEvent.observe(viewLifecycleOwner) { openRecipeEvent ->
            if (openRecipeEvent != null) {
                findNavController().navigate(
                    R.id.action_feedFragment_to_oneRecipeFragment,
                    Bundle().apply {
                        textId = openRecipeEvent.id.toString()
                    }
                )
            }
        }

        binding.favoriterecipeButton.setOnClickListener{
            findNavController().navigate(R.id.action_feedFragment_to_favoriteRecipeFragment)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

        binding.filterButton.setOnClickListener{
            findNavController().navigate(R.id.action_feedFragment_to_filtersFragment)
        }

        return binding.root
    }
}