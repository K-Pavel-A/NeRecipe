package ru.netology.nerecipe.activity

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Recipe(
    val id: Long,
    val title: String,
    val author: String,
    val likedByMe: Boolean = false,
    val category: String,
    val pictureUri: String,
    val step1Recipe: String,
    val step2Recipe: String,
    val step3Recipe: String,
    val step4Recipe: String,
    val step5Recipe: String,
) : Parcelable