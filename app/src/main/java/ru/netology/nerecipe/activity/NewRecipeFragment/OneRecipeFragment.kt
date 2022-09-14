package ru.netology.nerecipe.activity.NewRecipeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.activity.NewRecipeFragment.AppActivity.Companion.titleArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.authorArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.categoryArg
import ru.netology.nerecipe.activity.NewRecipeFragment.NewRecipeFragment.Companion.textArg
import ru.netology.nerecipe.activity.R
import ru.netology.nerecipe.activity.adapter.RecipeAdapter
import ru.netology.nerecipe.activity.databinding.FragmentOneRecipeBinding
import ru.netology.nerecipe.activity.utils.StringArg
import ru.netology.nerecipe.activity.viewModel.RecipeViewModel

class OneRecipeFragment: Fragment() {
    private val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOneRecipeBinding.inflate(inflater, container, false)

        val oneRecipeId = arguments?.textId

        val viewHolder = RecipeAdapter.ViewHolder(binding.onerecipe, viewModel)

        viewModel.data.observe(viewLifecycleOwner) { recipes ->
            val theRecipe = recipes.find {
                oneRecipeId?.toLong() == it.id
            }
            if (theRecipe != null) viewHolder.bind(theRecipe)

            binding.onerecipe.stepsofrecipeTextview.visibility = View.VISIBLE

            val popupMenu by lazy {
                PopupMenu(this.requireContext(), binding.onerecipe.optionsButton).apply {
                    inflate(R.menu.options_recipe)
                    setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.remove -> {
                                if (theRecipe != null)
                                    viewModel.onRemoveClicked(theRecipe)
                                findNavController().navigateUp()
                                true
                            }
                            R.id.edit -> {
                                if (theRecipe != null) {
                                    viewModel.onEditClicked(theRecipe)
                                }
                                findNavController().navigate(
                                    R.id.action_oneRecipeFragment_to_newRecipeFragment,
                                    Bundle().apply {
                                        if (theRecipe != null) {
                                            categoryArg = theRecipe.category
                                            authorArg = theRecipe.author
                                            titleArg = theRecipe.title
                                            textArg = theRecipe.stepsRecipe
                                        }
                                    })
                                true
                            }
                            else -> false
                        }
                    }
                }
            }
            binding.onerecipe.optionsButton.setOnClickListener { popupMenu.show() }
        }

        return binding.root
    }

    companion object{
        var Bundle.textId: String? by StringArg

    }
}
