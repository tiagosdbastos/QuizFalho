package com.example.quizfalho

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizfalho.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryBinding // Declare a variável de ligação

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_category)

        binding = ActivityCategoryBinding.inflate(layoutInflater) // Inicialize a variável de ligação
        setContentView(binding.root) //raiz da view de ligação como conteúdo da atividade

        val nomeCategoria = intent.getStringExtra("NOME_CATEGORIA")

        val imagemRes = when (nomeCategoria){ //recebe a categoria e retorna imagem correspondente

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

        // Ainda nao foi adicionado a proxima tela pra esso botao funcionar

        //binding.btnComecar.setOnClickListener {
        //    val intent = Intent(this, QuizActivity::class.java)
        //    intent.putExtra("NOME_CATEGORIA", nomeCategoria)
        //    startActivity(intent)
        //}

        binding.btnVoltar.setOnClickListener { // botao pra voltar pra tela anterior
            finish()
        }
    }
}