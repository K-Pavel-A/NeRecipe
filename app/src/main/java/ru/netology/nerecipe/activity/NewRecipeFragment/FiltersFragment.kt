package ru.netology.nerecipe.activity.NewRecipeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.activity.R
import ru.netology.nerecipe.activity.databinding.FragmentFiltersBinding
import ru.netology.nerecipe.activity.viewModel.RecipeViewModel

class FiltersFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFiltersBinding.inflate(inflater, container, false)


        binding.acceptFiltersButton.setOnClickListener {
            onAcceptButtonClicked(binding)
        }
            return binding.root
        }

    private fun onAcceptButtonClicked(binding: FragmentFiltersBinding){
        var filterCount = 0
        val amountOfFilters = 7

        if (!binding.checkBoxEuropean.isChecked) {
            ++filterCount
            viewModel.filterEuropean("Европейская")
        }
        if (!binding.checkBoxAsian.isChecked) {
            ++filterCount
            viewModel.filterAsian("Азиатская")
        }
        if (!binding.checkBoxPanasian.isChecked) {
            ++filterCount
            viewModel.filterPanasian("Паназиатская")
        }
        if (!binding.checkBoxEastern.isChecked) {
            ++filterCount
            viewModel.filterEastern("Восточная")
        }
        if (!binding.checkBoxAmerican.isChecked) {
            ++filterCount
            viewModel.filterAmerican("Американская")
        }
        if (!binding.checkBoxRussian.isChecked) {
            ++filterCount
            viewModel.filterRussian("Русская")
        }
        if (!binding.checkBoxMediterranean.isChecked) {
            ++filterCount
            viewModel.filterMediterranean( "Средиземноморская")
        }

        when (filterCount) {
            amountOfFilters -> {
                Toast.makeText(activity, "Вы не можете применить все фильтры", Toast.LENGTH_LONG).show()
                return
            }
            0 -> {
                findNavController().popBackStack()
            }
            else -> findNavController().navigate(R.id.action_filtersFragment_to_filteredRecipeFragment)
        }
    }
}