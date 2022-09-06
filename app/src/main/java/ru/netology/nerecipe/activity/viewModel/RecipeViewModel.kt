package ru.netology.nerecipe.activity.viewModel

import android.app.Application
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
//    val navigateToPostEvent = SingleLiveEvent<Long>()

    private val empty = Recipe(
        id = 0,
        content = "Здесь будет рецепт",
        author = "Me",
        likedByMe = false,
        category = "Азиатская",
        title = "Стейки"
    )

    override fun onLikedClicked(recipe: Recipe) = repository.like(recipe.id)

    override fun onRemoveClicked(recipe: Recipe) = repository.delete(recipe.id)

    override fun onEditClicked(recipe: Recipe) {
        currentRecipe.value = recipe
        editEvent.value = recipe
    }

    fun onSaveButtonClicked(content:String){
        if (content.isBlank()) return
        val recipe = currentRecipe.value?.copy(content = content) ?: Recipe (
            id = RecipeRepository.NEW_RECIPE_ID,
            author = "Me",
            content = content,
            category = "Категория",
            title = "Стейки"
        )
        repository.save(recipe)
        currentRecipe.value = null
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (currentRecipe.value?.content == text) {
            return
        }
        currentRecipe.value = currentRecipe.value?.copy(content = text)
    }

    fun save() {
        currentRecipe.value?.let {
            repository.save(it)
        }
        currentRecipe.value = empty
    }

}