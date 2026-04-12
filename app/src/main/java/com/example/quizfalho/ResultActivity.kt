package com.example.quizfalho

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizfalho.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Pega a pontuação que veio da CategoryActivity
        val score = intent.getIntExtra("PONTUACAO", 0)
        binding.txtPontosFinal.text = "Você fez $score pontos!"

        when {
            score <= 40 -> {
                binding.txtEmoji.text = "🌱"
                binding.txtFeedback.text = "Não desanime! Cada erro é um passo para o aprendizado. Vamos tentar de novo com carinho? ✨"
                binding.layoutResultado.setBackgroundColor(Color.parseColor("#0F0E17")) // Laranja fofo
            }
            score <= 70 -> {
                binding.txtEmoji.text = "🚀"
                binding.txtFeedback.text = "Muito bem! Você teve um ótimo aproveitamento. Só falta um pouquinho para chegar ao topo! Que tal mais uma chance?"
                binding.layoutResultado.setBackgroundColor(Color.parseColor("#0F0E17")) // Azul fofo
            }
            else -> {
                binding.txtEmoji.text = "👑"
                binding.txtFeedback.text = "INCRÍVEL! Você brilhou muito! Seu desempenho foi nota dez, parabéns por todo esse conhecimento! 🏆"
                binding.layoutResultado.setBackgroundColor(Color.parseColor("#0F0E17")) // Verde fofo
            }
        }

        binding.btnSair.setOnClickListener {
            finish() // Fecha e volta para a tela anterior
        }
    }
}