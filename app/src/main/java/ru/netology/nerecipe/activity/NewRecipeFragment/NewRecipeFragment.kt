package ru.netology.nerecipe.activity.NewRecipeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
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

        var categoryRecipe = arguments?.categoryArg
        var pictureUri = arguments?.uriArg.toString()

        val image = registerForActivityResult(ActivityResultContracts.OpenDocument()){
            binding.avatarRecipeView.setImageURI(it)
            pictureUri = it.toString()
        }

        binding.avatarRecipeView.setOnClickListener{
            image.launch(arrayOf("image/*"))
        }

        arguments?.categoryArg?.let(binding.categoryTextview::setText)
        arguments?.authorArg?.let(binding.authorName::setText)
        arguments?.titleArg?.let(binding.title::setText)
        arguments?.step1Arg?.let(binding.recipestep1Text::setText)
        arguments?.step2Arg?.let(binding.recipestep2Text::setText)
        arguments?.step3Arg?.let(binding.recipestep3Text::setText)
        arguments?.step4Arg?.let(binding.recipestep4Text::setText)
        arguments?.step5Arg?.let(binding.recipestep5Text::setText)


        binding.recipestep1Text.visibility = View.VISIBLE
        if(!arguments?.step2Arg.isNullOrBlank()){
            binding.step2Group.visibility = View.VISIBLE
        }
        if(!arguments?.step3Arg.isNullOrBlank()){
            binding.step3Group.visibility = View.VISIBLE
        }
        if(!arguments?.step4Arg.isNullOrBlank()){
            binding.step4Group.visibility = View.VISIBLE
        }
        if(!arguments?.step5Arg.isNullOrBlank()){
            binding.step5Group.visibility = View.VISIBLE
        }


        binding.setCategoryButton.setOnClickListener{
            with(binding.categoryRecipeCheckBox){
                visibility = if(visibility == View.GONE) View.VISIBLE else View.GONE
                setOnCheckedChangeListener { _, i ->
                    when (i) {
                        R.id.checkBoxEuropean -> categoryRecipe = "Европейская"
                        R.id.checkBoxAsian -> categoryRecipe = "Азиатская"
                        R.id.checkBoxPanasian -> categoryRecipe = "Паназиатская"
                        R.id.checkBoxEastern -> categoryRecipe = "Восточная"
                        R.id.checkBoxAmerican -> categoryRecipe = "Американская"
                        R.id.checkBoxRussian -> categoryRecipe = "Русская"
                        R.id.checkBoxMediterranean -> categoryRecipe = "Средиземноморская"
                    }
                    binding.categoryTextview.text = categoryRecipe
                    visibility = View.GONE
                }
            }
        }

        binding.ok.setOnClickListener {
            binding.categoryTextview.text = categoryRecipe
            if (!binding.title.text.isNullOrBlank()
                && !binding.authorName.text.isNullOrBlank()
                && !binding.recipestep1Text.text.isNullOrBlank()
                && !binding.categoryTextview.text.isNullOrBlank()
            ){
                viewModel.onSaveButtonClicked(
                    binding.authorName.text.toString(),
                    binding.recipestep1Text.text.toString(),
                    binding.recipestep2Text.text.toString(),
                    binding.recipestep3Text.text.toString(),
                    binding.recipestep4Text.text.toString(),
                    binding.recipestep5Text.text.toString(),
                    binding.title.text.toString(),
                    binding.categoryTextview.text.toString(),
                    pictureUri = pictureUri,
                )
                findNavController().navigateUp()
            } else {
                Toast.makeText(activity, "Все поля должны быть заполнены", Toast.LENGTH_LONG).show()
            }
        }

        binding.step1Button.setOnClickListener{
            binding.step2Group.visibility = View.VISIBLE
            binding.recipestep2Button.setOnClickListener{
                binding.step3Group.visibility = View.VISIBLE
                binding.recipestep3Button.setOnClickListener{
                    binding.step4Group.visibility = View.VISIBLE
                    binding.recipestep4Button.setOnClickListener{
                        binding.step5Group.visibility = View.VISIBLE
                    }
                }
            }
        }

        return binding.root
    }

    companion object{
        var Bundle.categoryArg: String? by StringArg
        var Bundle.authorArg:String? by StringArg
        var Bundle.titleArg:String? by StringArg
        var Bundle.step1Arg:String? by StringArg
        var Bundle.uriArg: String? by StringArg
        var Bundle.step2Arg:String? by StringArg
        var Bundle.step3Arg:String? by StringArg
        var Bundle.step4Arg:String? by StringArg
        var Bundle.step5Arg:String? by StringArg
    }
}