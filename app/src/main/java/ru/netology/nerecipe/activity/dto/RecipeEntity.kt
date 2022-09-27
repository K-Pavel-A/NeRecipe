package ru.netology.nerecipe.activity.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "step1Recipe")
    val step1Recipe: String,
    @ColumnInfo(name = "step2Recipe")
    val step2Recipe: String,
    @ColumnInfo(name = "step3Recipe")
    val step3Recipe: String,
    @ColumnInfo(name = "step4Recipe")
    val step4Recipe: String,
    @ColumnInfo(name = "step5Recipe")
    val step5Recipe: String,
    @ColumnInfo(name = "likedByMe")
    val likedByMe: Boolean,
    @ColumnInfo(name = "pictureUri")
    val pictureUri: String,

)