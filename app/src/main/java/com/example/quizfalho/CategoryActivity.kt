package com.example.quizfalho

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.quizfalho.databinding.ActivityCategoryBinding
import android.view.View

class CategoryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCategoryBinding
    private var nomeCategoria: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nomeCategoria = intent.getStringExtra("NOME_CATEGORIA")

        val imagemRes = when (nomeCategoria) {
            "Historia" -> R.drawable.ic_school
            "Ciencias" -> R.drawable.ic_ciencia
            "Sports" -> R.drawable.ic_sports
            "Art" -> R.drawable.ic_art
            else -> R.drawable.ic_launcher_background
        }

        binding.imagemCategoria.setImageResource(imagemRes)
        binding.txtNomeCategoria.text = nomeCategoria

        setListeners()
    }

    private fun setListeners() {
        binding.btnComecar.setOnClickListener(this)
        binding.btnVoltar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_comecar -> {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("NOME_CATEGORIA", nomeCategoria)
                startActivity(intent)
            }
            R.id.btn_voltar -> finish()
        }
    }
}