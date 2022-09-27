package ru.netology.nerecipe.activity.dto

import ru.netology.nerecipe.activity.Recipe

internal fun RecipeEntity.toModel() = Recipe(
    id = id,
    title = title,
    author = author,
    category = category,
    step1Recipe = step1Recipe,
    step2Recipe = step2Recipe,
    step3Recipe = step3Recipe,
    step4Recipe = step4Recipe,
    step5Recipe = step5Recipe,
    likedByMe = likedByMe,
    pictureUri = pictureUri
)

internal fun Recipe.toEntity() = RecipeEntity(
    id = id,
    title = title,
    author = author,
    category = category,
    step1Recipe = step1Recipe,
    step2Recipe = step2Recipe,
    step3Recipe = step3Recipe,
    step4Recipe = step4Recipe,
    step5Recipe = step5Recipe,
    likedByMe = likedByMe,
    pictureUri = pictureUri
)