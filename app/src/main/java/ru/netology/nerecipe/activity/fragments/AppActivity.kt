package ru.netology.nerecipe.activity.fragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import ru.netology.nerecipe.R
import ru.netology.nerecipe.databinding.ActivityAppBinding
//import ru.netology.nmedia.util.StringArg

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {
            if (it.action != Intent.ACTION_SEND) {
                return@let
            }
////
////            val text = it.getStringExtra(Intent.EXTRA_TEXT)
////            if (text.isNullOrBlank()) {
////                Snackbar.make(binding.root, "Content can't be empty", LENGTH_INDEFINITE)
////                    .setAction(android.R.string.ok) {
////                        finish()
////                    }
////                    .show()
////                return@let
////            }
////            findNavController(R.id.nav_host_fragment).navigate(
////                R.id.action_feedFragment_to_newPostFragment, Bundle().apply { textArg = text }
////            )
////        }
////    }
////    companion object{
////        var Bundle.textArg: String? by StringArg
        }
    }
}