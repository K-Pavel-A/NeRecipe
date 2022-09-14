package ru.netology.nerecipe.activity

import android.widget.RadioGroup
import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Long,
    val title: String,
    val author: String,
    val stepsRecipe: String,
    val likedByMe: Boolean = false,
    val category: String
)