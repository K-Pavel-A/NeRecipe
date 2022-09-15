package ru.netology.nerecipe.activity.NewRecipeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.activity.R
import ru.netology.nerecipe.activity.databinding.FragmentFavoriteRecipeBinding
import ru.netology.nerecipe.activity.databinding.FragmentNewRecipeBinding
import ru.netology.nerecipe.activity.utils.StringArg
import ru.netology.nerecipe.activity.viewModel.RecipeViewModel

class FavoriteRecipeFragment: Fragment() {
    private val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFavoriteRecipeBinding.inflate(inflater, container, false)

        return binding.root
    }

//
//    companion object{
//        var Bundle.categoryArg: String? by StringArg
//        var Bundle.authorArg:String? by StringArg
//        var Bundle.titleArg:String? by StringArg
//        var Bundle.textArg:String? by StringArg
//    }
}