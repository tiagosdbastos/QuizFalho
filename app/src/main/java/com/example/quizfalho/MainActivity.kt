package com.example.quizfalho

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizfalho.databinding.ActivityMainBinding
import android.view.View
import android.content.Intent

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding // Declare a variável de ligação

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater) // Inicialize a variável de ligação
        setContentView(binding.root) //raiz da view de ligação como conteúdo da atividade

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListeners()
    }
    private fun setListeners() { //aqui ficam os listeners de cada card   colocar escutadores
        binding.cardHistoria.setOnClickListener(this)
        binding.cardCiencias.setOnClickListener(this)
        binding.cardEsportes.setOnClickListener(this)
        binding.cardArte.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cardHistoria -> {
//                println("Clicou no card História")
                val intent = Intent(this, CategoryActivity::class.java)
                intent.putExtra("NOME_CATEGORIA", "Historia")
                startActivity(intent)
            }
            R.id.cardCiencias -> {
//                println("Clicou no card Ciências")
                val intent = Intent(this, CategoryActivity::class.java)
                intent.putExtra("NOME_CATEGORIA", "Ciencias")
                startActivity(intent)
            }
            R.id.cardEsportes -> {
//                println("Clicou no card Esportes")
                val intent = Intent(this, CategoryActivity::class.java)
                intent.putExtra("NOME_CATEGORIA", "Sports")
                startActivity(intent)
            }
            R.id.cardArte -> {
//                println("Clicou no card Arte")
                val intent = Intent(this, CategoryActivity::class.java)
                intent.putExtra("NOME_CATEGORIA", "Art")
                startActivity(intent)
            }
        }

        }
    }