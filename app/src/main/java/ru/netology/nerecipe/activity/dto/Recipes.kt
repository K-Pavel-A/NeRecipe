package ru.netology.nerecipe.activity.dto

import ru.netology.nerecipe.activity.Recipe

internal fun RecipeEntity.toModel() = Recipe(
    id = id,
    title = title,
    author = author,
    category = category,
    stepsRecipe = stepsRecipe,
    likedByMe = likedByMe,
)

internal fun Recipe.toEntity() = RecipeEntity(
    id = id,
    title = title,
    author = author,
    category = category,
    stepsRecipe = stepsRecipe,
    likedByMe = likedByMe,
)