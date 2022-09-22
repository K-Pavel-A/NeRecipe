package ru.netology.nerecipe.activity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Recipe(
    val id: Long,
    val title: String,
    val author: String,
    val stepsRecipe: String,
    val likedByMe: Boolean = false,
    val category: String
) : Parcelable