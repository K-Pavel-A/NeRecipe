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

    override fun onRemoveClicked(recipe: Recipe) = repository.delete(recipe)

    override fun onEditClicked(recipe: Recipe) {
        currentRecipe.value = recipe
        editEvent.value = recipe
    }

    fun onSaveButtonClicked(
        author: String,
        step1Recipe: String,
        step2Recipe: String,
        step3Recipe: String,
        step4Recipe: String,
        step5Recipe: String,
        title: String,
        category: String,
        pictureUri: String
    ) {
        if (step1Recipe.isBlank()) return
        val recipe = currentRecipe.value?.copy(
            step1Recipe = step1Recipe, step2Recipe = step2Recipe, step3Recipe = step3Recipe, step4Recipe = step4Recipe,
            step5Recipe = step5Recipe, author = author, title = title, category = category, pictureUri = pictureUri) ?: Recipe (
            id = RecipeRepository.NEW_RECIPE_ID,
            author = author,
            category = category,
            title = title,
            pictureUri = pictureUri,
            step1Recipe = step1Recipe,
            step2Recipe = step2Recipe,
            step3Recipe = step3Recipe,
            step4Recipe = step4Recipe,
            step5Recipe = step5Recipe
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
    }

    fun filterPanasian(category: String) {
        repository.filterPanasian(category)
    }

    fun filterEastern(category: String) {
        repository.filterEastern(category)
    }

    fun filterAmerican(category: String) {
        repository.filterAmerican(category)
    }

    fun filterRussian(category: String) {
        repository.filterRussian(category)
    }

    fun filterMediterranean(category: String) {
        repository.filterMediterranean(category)
    }

    fun filterEuropean(category: String) {
        repository.filterEuropean(category)
    }

}