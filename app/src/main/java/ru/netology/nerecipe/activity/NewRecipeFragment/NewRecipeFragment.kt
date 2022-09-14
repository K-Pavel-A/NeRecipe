package ru.netology.nerecipe.activity.NewRecipeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.activity.R
import ru.netology.nerecipe.activity.databinding.FragmentNewRecipeBinding
import ru.netology.nerecipe.activity.utils.StringArg
import ru.netology.nerecipe.activity.viewModel.RecipeViewModel

class NewRecipeFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewRecipeBinding.inflate(inflater, container, false)

        var categoryRecipe = ""

        arguments?.categoryArg?.let(binding.categoryTextview::setText)
        arguments?.authorArg?.let(binding.authorName::setText)
        arguments?.titleArg?.let(binding.title::setText)
        arguments?.textArg?.let(binding.textRecipe::setText)

        binding.setCategoryButton.setOnClickListener{
            with(binding.categoryRecipeCheckBox){
                visibility = View.VISIBLE
                setOnCheckedChangeListener { _, i ->
                    when (i) {
                        R.id.checkBoxEuropean -> categoryRecipe = "European"
                        R.id.checkBoxAsian -> categoryRecipe = "Asian"
                        R.id.checkBoxPanasian -> categoryRecipe = "Panasian"
                        R.id.checkBoxEastern -> categoryRecipe = "Eastern"
                        R.id.checkBoxAmerican -> categoryRecipe = "American"
                        R.id.checkBoxRussian -> categoryRecipe = "Russian"
                        R.id.checkBoxMediterranean -> categoryRecipe = "Mediterranean"
                    }
                    binding.categoryTextview.text = categoryRecipe
                    visibility = View.GONE
                }
            }
        }

        binding.ok.setOnClickListener {
            binding.categoryTextview.text = categoryRecipe
            if (!binding.title.text.isNullOrBlank()){
                viewModel.onSaveButtonClicked(
                    binding.authorName.text.toString(),
                    binding.textRecipe.text.toString(),
                    binding.title.text.toString(),
                    binding.categoryTextview.text.toString()
                )
            }
            findNavController().navigateUp()
        }

        return binding.root
    }


    companion object{
        var Bundle.categoryArg: String? by StringArg
        var Bundle.authorArg:String? by StringArg
        var Bundle.titleArg:String? by StringArg
        var Bundle.textArg:String? by StringArg
    }
}