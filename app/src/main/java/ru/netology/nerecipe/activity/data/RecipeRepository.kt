package ru.netology.nerecipe.activity.data

import androidx.lifecycle.LiveData
import ru.netology.nerecipe.activity.Recipe

interface RecipeRepository {
    val data: LiveData<List<Recipe>>
    fun like(recipeId:Long)
    fun delete(recipeId: Long)
    fun save(recipe: Recipe)

    companion object{
        const val NEW_RECIPE_ID = 0L
    }
}