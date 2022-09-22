package ru.netology.nerecipe.activity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.netology.nerecipe.activity.dto.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    fun getAll(): LiveData<List<RecipeEntity>>

    @Insert
    fun save(recipe: RecipeEntity)

    @Query(
        "UPDATE recipes SET " +
                "title = :title," +
                "author = :author, " +
                "category = :category," +
                "stepsRecipe = :stepsRecipe" +
                " WHERE id = :id"
    )
    fun updateRecipeById(
        id: Long, title: String, author: String,
        category: String, stepsRecipe: String
    )

    @Query(
        """
        UPDATE recipes SET
        likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
        WHERE id = :id
        """
    )
    fun likeById(id: Long)

    @Query("DELETE FROM recipes WHERE id = :id")
    fun removeById(id: Long)

    @Query("SELECT * FROM recipes WHERE category = :category")
    fun getEuropean(category: String): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :text || '%'")
    fun searchByText(text: String): LiveData<List<RecipeEntity>>
}

