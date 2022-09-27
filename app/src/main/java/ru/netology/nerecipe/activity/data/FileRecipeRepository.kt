package ru.netology.nerecipe.activity.data

import ru.netology.nerecipe.activity.Recipe
import ru.netology.nerecipe.activity.dao.RecipeDao
import ru.netology.nerecipe.activity.dto.toModel
import androidx.lifecycle.map
import ru.netology.nerecipe.activity.dto.toEntity


class FileRecipeRepository(
    private val dao: RecipeDao
) : RecipeRepository {

    override var data = dao.getAll().map { entities ->
        entities.map { it.toModel() }
    }

    override fun getData() {
        data = dao.getAll().map { entities ->
            entities.map { it.toModel() }
        }
    }

    override fun filterEuropean(type: String) {
        data = data.map {
            it.filter { it.category != type }
        }
    }

    override fun filterAsian(type: String) {
        data = data.map {
            it.filter { it.category != type }
        }
    }

    override fun filterPanasian(type: String) {
        data = data.map {
            it.filter { it.category != type }
        }
    }

    override fun filterEastern(type: String) {
        data = data.map {
            it.filter { it.category != type }
        }
    }

    override fun filterAmerican(type: String) {
        data = data.map {
            it.filter { it.category != type }
        }
    }

    override fun filterRussian(type: String) {
        data = data.map {
            it.filter { it.category != type }
        }
    }

    override fun filterMediterranean(type: String) {
        data = data.map {
            it.filter { it.category != type }
        }
    }

    override fun like(long: Long) {
        dao.likeById(long)
    }

    override fun delete(recipe: Recipe) {
        dao.removeById(recipe.id)
    }

    override fun save(recipe: Recipe) {
        if (recipe.id == RecipeRepository.NEW_RECIPE_ID) dao.save(recipe = recipe.toEntity())
        else dao.updateRecipeById(
            recipe.id, recipe.title, recipe.author, recipe.category, recipe.step1Recipe,recipe.step2Recipe,
            recipe.step3Recipe,recipe.step4Recipe, recipe.step5Recipe, recipe.pictureUri
        )
    }

    private fun update(recipe: Recipe) {
        save(recipe)
    }

    override fun searchText(Text: String) {
        data = dao.searchByText(Text).map { entities ->
            entities.map { it.toModel() }
        }
    }


}

