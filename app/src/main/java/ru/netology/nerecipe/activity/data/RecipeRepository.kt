package ru.netology.nerecipe.activity.data

import androidx.lifecycle.LiveData
import ru.netology.nerecipe.activity.Recipe

interface RecipeRepository {
    val data: LiveData<List<Recipe>>
    fun like(long:Long)
    fun delete(recipe: Recipe)
    fun save(recipe: Recipe)
    fun searchText(Text: String)
    fun getData()
//    fun categoryFilter(type:String)
    fun filterEuropean(type: String)
    fun filterAsian(type: String)
    fun filterPanasian(type: String)
    fun filterEastern(type: String)
    fun filterAmerican(type: String)
    fun filterRussian(type: String)
    fun filterMediterranean(type: String)


    companion object{
        const val NEW_RECIPE_ID = 0L
    }
}