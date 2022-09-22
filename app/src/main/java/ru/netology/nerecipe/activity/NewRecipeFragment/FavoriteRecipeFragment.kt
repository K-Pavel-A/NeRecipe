package ru.netology.nerecipe.activity.NewRecipeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.activity.R
import ru.netology.nerecipe.activity.adapter.RecipeAdapter
import ru.netology.nerecipe.activity.databinding.FragmentFavoriteRecipeBinding
import ru.netology.nerecipe.activity.databinding.FragmentNewRecipeBinding
import ru.netology.nerecipe.activity.utils.StringArg
import ru.netology.nerecipe.activity.viewModel.RecipeViewModel

class FavoriteRecipeFragment : Fragment() {
    private val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavoriteRecipeBinding.inflate(inflater, container, false)

        viewModel.data.observe(viewLifecycleOwner) { recipes ->
            val adapter = RecipeAdapter(viewModel)
            binding.favoriteRecipesRecyclerView.adapter = adapter

            viewModel.data.observe(viewLifecycleOwner) { recipes ->
                val favRecipes = recipes.filter { it.likedByMe }
                adapter.submitList(favRecipes)
            }
        }

        binding.favoriteFeedButton.setOnClickListener{
            findNavController().navigate(R.id.action_favoriteRecipeFragment_to_feedFragment)
        }
        return binding.root

//        binding.favoriteFavoriterecipeButton.setOnClickListener{
//            findNavController().navigate(R.id.action_filteredRecipeFragment_to_favoriteRecipeFragment)
//        }
    }

}