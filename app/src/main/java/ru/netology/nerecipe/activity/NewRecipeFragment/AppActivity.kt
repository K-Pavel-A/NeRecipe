package ru.netology.nerecipe.activity.NewRecipeFragment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import ru.netology.nerecipe.activity.R
import ru.netology.nerecipe.activity.databinding.ActivityAppBinding
import ru.netology.nerecipe.activity.utils.StringArg



class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {
            if (it.action != Intent.ACTION_SEND) {
                return@let
            }

            val text = it.getStringExtra(Intent.EXTRA_TEXT)
            val author = it.getStringExtra(Intent.EXTRA_TEXT)
            val title = it.getStringExtra(Intent.EXTRA_TEXT)
            val category = it.getStringExtra(Intent.EXTRA_TEXT)

            if (text.isNullOrBlank()) {
                Snackbar.make(binding.root, "Content can't be empty", LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok) {
                        finish()
                    }
                    .show()
                return@let
            }
            findNavController(R.id.nav_host_fragment).navigate(
                R.id.action_feedFragment_to_newRecipeFragment, Bundle().apply {
                    textArg = text
                    authorArg = author
                    titleArg = title
                    categoryArg = category
                }
            )
        }
    }

    companion object {
        var Bundle.textArg: String? by StringArg
        var Bundle.authorArg:String? by StringArg
        var Bundle.titleArg:String? by StringArg
        var Bundle.categoryArg:String? by StringArg
    }

}
