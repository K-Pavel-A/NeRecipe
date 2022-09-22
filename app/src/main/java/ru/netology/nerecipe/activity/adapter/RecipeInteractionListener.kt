package ru.netology.nerecipe.activity.adapter

import ru.netology.nerecipe.activity.Recipe

interface RecipeInteractionListener {
    fun onLikeClicked(long: Long)
    fun onRemoveClicked(recipe: Recipe)
    fun onEditClicked(recipe: Recipe)
    fun onRecipeClicked(recipe: Recipe)
    fun onSearchClicked(text:String)
}