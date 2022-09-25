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
                "step1Recipe = :step1Recipe," +
                "step2Recipe = :step2Recipe," +
                "step3Recipe = :step3Recipe," +
                "step4Recipe = :step4Recipe," +
                "step5Recipe = :step5Recipe," +
                "pictureUri = :pictureUri"+
                " WHERE id = :id"
    )
    fun updateRecipeById(
        id: Long, title: String, author: String,
        category: String, step1Recipe:String, step2Recipe:String, step3Recipe:String, step4Recipe:String, step5Recipe:String, pictureUri: String
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

