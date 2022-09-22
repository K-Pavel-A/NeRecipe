package ru.netology.nerecipe.activity.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nerecipe.activity.Recipe
import ru.netology.nerecipe.activity.SingleLiveEvent
import ru.netology.nerecipe.activity.adapter.RecipeInteractionListener
import ru.netology.nerecipe.activity.data.FileRecipeRepository
import ru.netology.nerecipe.activity.data.RecipeRepository
import ru.netology.nerecipe.activity.db.AppDb

class RecipeViewModel(
    application: Application
): AndroidViewModel(application), RecipeInteractionListener {

    private val repository: RecipeRepository =
        FileRecipeRepository(dao = AppDb.getInstance(context = application).recipeDao)

    val data by repository::data

    val currentRecipe = MutableLiveData<Recipe?>(null)
    val editEvent = SingleLiveEvent<Recipe?>()
    val openRecipeEvent = SingleLiveEvent<Recipe?>()
    val filterFragment = SingleLiveEvent<Recipe?>()
    var filterOn = false

    override fun onRemoveClicked(recipe: Recipe) = repository.delete(recipe)

    override fun onEditClicked(recipe: Recipe) {
        currentRecipe.value = recipe
        editEvent.value = recipe
    }

    fun onSaveButtonClicked(author:String, stepsRecipe:String, title:String, category: String){
        if (stepsRecipe.isBlank()) return
        val recipe = currentRecipe.value?.copy(stepsRecipe = stepsRecipe, author = author, title = title, category = category) ?: Recipe (
            id = RecipeRepository.NEW_RECIPE_ID,
            author = author,
            stepsRecipe = stepsRecipe,
            category = category,
            title = title
        )
        repository.save(recipe)
        currentRecipe.value = null
    }

    override fun onRecipeClicked(recipe: Recipe) {
        openRecipeEvent.value = recipe
    }

    override fun onLikeClicked(recipeId: Long) {
        repository.like(recipeId)
    }

    override fun onSearchClicked(text: String) {
        repository.searchText(text)
    }


    fun clearFilter() {
        repository.getData()
    }


    fun filterAsian(category: String) {
        repository.filterAsian(category)
        filterOn = true
    }

    fun filterPanasian(category: String) {
        repository.filterPanasian(category)
        filterOn = true
    }

    fun filterEastern(category: String) {
        repository.filterEastern(category)
        filterOn = true
    }

    fun filterAmerican(category: String) {
        repository.filterAmerican(category)
        filterOn = true
    }

    fun filterRussian(category: String) {
        repository.filterRussian(category)
        filterOn = true
    }

    fun filterMediterranean(category: String) {
        repository.filterMediterranean(category)
        filterOn = true
    }

    fun filterEuropean(category: String) {
        repository.filterEuropean(category)
        filterOn = true
    }

}