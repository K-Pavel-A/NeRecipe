package ru.netology.nerecipe.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nerecipe.activity.R
import ru.netology.nerecipe.activity.Recipe
import ru.netology.nerecipe.activity.databinding.RecipesBinding


class RecipeAdapter(
    private val interactionListener: RecipeInteractionListener
)
    : ListAdapter<Recipe, RecipeAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    class ViewHolder(

        private val binding: RecipesBinding,
        listener: RecipeInteractionListener,
    )
        : RecyclerView.ViewHolder(binding.root) {

        private lateinit var recipe: Recipe

        private val popupMenu by lazy {
            PopupMenu(itemView.context, binding.optionsButton).apply{
                inflate(R.menu.options_recipe)
                setOnMenuItemClickListener { menuItem->
                    when (menuItem.itemId){
                        R.id.remove ->{
                            listener.onRemoveClicked(recipe)
                            true
                        }
                        R.id.edit -> {
                            listener.onEditClicked(recipe)
                            true
                        }
                        else -> false
                    }
                }
            }
        }

        init {
            binding.likerecipeButton.setOnClickListener {
                listener.onLikeClicked(recipe.id)
            }
            binding.optionsButton.setOnClickListener { popupMenu.show() }

            binding.root.setOnClickListener{
                listener.onRecipeClicked(recipe)
            }
        }

        fun bind(recipe: Recipe){

            this.recipe = recipe

            with(binding) {
                titleTextview.text = recipe.title
                authornameTextview.text = recipe.author
                categoryTextview.text = recipe.category
                likerecipeButton.isChecked = recipe.likedByMe
                stepsofrecipeTextview.text = recipe.stepsRecipe
            }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem == newItem
    }
}