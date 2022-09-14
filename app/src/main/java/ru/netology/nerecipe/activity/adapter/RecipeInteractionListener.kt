package ru.netology.nerecipe.activity.adapter

import ru.netology.nerecipe.activity.Recipe

interface RecipeInteractionListener {
    fun onLikedClicked(recipe: Recipe)
    fun onRemoveClicked(recipe: Recipe)
    fun onEditClicked(recipe: Recipe)
    fun onRecipeClicked(recipe: Recipe)
    fun onFavoriteClicked(recipe: Recipe)
}