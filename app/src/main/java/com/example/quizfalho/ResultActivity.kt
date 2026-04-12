package com.example.quizfalho

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizfalho.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pontuacao = intent.getIntExtra("PONTUACAO", 0)
        binding.textPontuacao.text = "$pontuacao / 100%"

        Toast.makeText(this, "Você fez $pontuacao pontos!", Toast.LENGTH_LONG).show()

        if (pontuacao == 100) {
            binding.imageBanner.visibility = View.VISIBLE
        }

        salvarPontuacao(pontuacao)
        setListeners()

    }

    private fun setListeners() {
        binding.botaoVoltarInicio.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.botaoVoltarInicio) {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    private fun salvarPontuacao(pontuacao: Int) {
        val prefs = getSharedPreferences("quiz_prefs", MODE_PRIVATE)
        prefs.edit().putInt("ultima_pontuacao", pontuacao).apply()
    }
}