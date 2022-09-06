package ru.netology.nerecipe.activity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nerecipe.activity.Recipe
import ru.netology.nerecipe.databinding.RecipesBinding


class RecipeAdapter(
    private val interactionListener: RecipeInteractionListener
)
    : ListAdapter<Recipe, RecipeAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipesBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener,
        )
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

//        private val popupMenu by lazy {
//            PopupMenu(itemView.context, binding.optionsButtonview).apply{
//                inflate(R.menu.options_post)
//                setOnMenuItemClickListener { menuItem->
//                    when (menuItem.itemId){
//                        R.id.remove ->{
//                            listener.onRemoveClicked(post)
//                            true
//                        }
//                        R.id.edit -> {
//                            listener.onEditClicked(post)
//                            true
//                        }
//                        else -> false
//                    }
//                }
//            }
//        }

//        init {
//            binding.likesButtonview.setOnClickListener {
//                listener.onLikedClicked(post)
//            }
//            binding.shareButtonview.setOnClickListener {
//                listener.onShareClicked(post)
//            }
//            binding.optionsButtonview.setOnClickListener { popupMenu.show() }
//
//        }

        fun bind(recipe: Recipe){

            this.recipe = recipe

            with(binding) {
                titleTextview.text = recipe.title
                authornameTextview.text = recipe.author
                categoryTextview.text = recipe.category
                likerecipeButton.isChecked = recipe.likedByMe
            }
        }
//        private fun amountFormat(number: Int): String {
//            var text = ""
//            when {
//                number > 1_099_999 -> text = "${number / 1000000}.${(number % 1000000) / 100000}М"
//                number > 999_999 -> text = "1М"
//                number > 9_999 -> text = "${number / 1000}К"
//                number > 1_099 -> text = "${(number / 1000)}.${(number % 1000) / 100}К"
//                number > 999 -> text = "1К"
//                number >= 0 -> text = "$number"
//            }
//            return text
//        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem == newItem
    }
}