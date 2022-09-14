package ru.netology.nerecipe.activity.viewModel

import android.app.Application
import android.app.usage.UsageEvents
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nerecipe.activity.Recipe
import ru.netology.nerecipe.activity.SingleLiveEvent
import ru.netology.nerecipe.activity.adapter.RecipeInteractionListener
import ru.netology.nerecipe.activity.data.FileRecipeRepository
import ru.netology.nerecipe.activity.data.RecipeRepository


class RecipeViewModel(
    application: Application
): AndroidViewModel(application), RecipeInteractionListener {

    private val repository: RecipeRepository =
        FileRecipeRepository(application)

    val data by repository::data

    val currentRecipe = MutableLiveData<Recipe?>(null)
    val editEvent = SingleLiveEvent<Recipe?>()
    val openRecipeEvent = SingleLiveEvent<Recipe>()
    val filteredRecipe = MutableLiveData<Recipe?>(null)

    override fun onLikedClicked(recipe: Recipe) = repository.like(recipe.id)

    override fun onRemoveClicked(recipe: Recipe) = repository.delete(recipe.id)

    override fun onEditClicked(recipe: Recipe) {
        currentRecipe.value = recipe
        editEvent.value = recipe
    }

    fun onSaveButtonClicked(author:String, stepsRecipe:String, title:String, category: String){
        if (stepsRecipe.isBlank()) return
        val recipe = currentRecipe.value?.copy(stepsRecipe = stepsRecipe, author = author, title = title) ?: Recipe (
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

    override fun onFavoriteClicked(recipe: Recipe) {
        repository.favorite(recipe)
    }


}