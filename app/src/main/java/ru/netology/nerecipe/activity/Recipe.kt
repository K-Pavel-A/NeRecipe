package ru.netology.nerecipe.activity

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val id: Long,
    val title: String,
    val author: String,
    val content: String,
    val likedByMe: Boolean = false,
    val category: String
)