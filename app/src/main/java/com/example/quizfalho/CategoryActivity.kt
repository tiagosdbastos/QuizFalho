package com.example.quizfalho

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizfalho.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_category)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var nomeCategoria = intent.getStringExtra("NOME_CATEGORIA")

        var imagemRes = when (nomeCategoria){

         "Historia" -> R.drawable.ic_school
         "Ciencias" -> R.drawable.ic_ciencia
         "Sports" -> R.drawable.ic_sports
         "Art" -> R.drawable.ic_art
           else -> R.drawable.ic_launcher_background

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imagemCategoria.setImageResource(imagemRes)
    }
}