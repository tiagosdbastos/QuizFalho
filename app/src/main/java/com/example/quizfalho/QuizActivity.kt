package com.example.quizfalho

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizfalho.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private var questions: List<Question> = listOf()
    private val radioGroups = mutableListOf<RadioGroup>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nomeCategoria = intent.getStringExtra("NOME_CATEGORIA") ?: "Quiz"
        questions = QuestionRepository.getQuestionsByCategory(nomeCategoria)

        if (questions.isEmpty()) {
            Toast.makeText(this, "Nenhuma pergunta encontrada!", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        montarQuestionario()

        binding.btnEnviar.setOnClickListener {
            if (todasRespondidas()) {
                val score = calcularPontuacao()
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("PONTUACAO", score)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Responda todas as perguntas!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun montarQuestionario() {
        questions.forEachIndexed { index, question ->

            val txtPergunta = TextView(this)
            txtPergunta.text = "${index + 1}. ${question.text}"
            txtPergunta.textSize = 18f
            txtPergunta.setTextColor(getColor(R.color.textoPrimario))
            txtPergunta.setPadding(0, 32, 0, 8)

            binding.containerPerguntas.addView(txtPergunta, index * 2)

            val radioGroup = RadioGroup(this)

            question.options.forEach { opcao ->
                val rb = RadioButton(this)
                rb.text = opcao
                rb.setTextColor(getColor(R.color.textoPrimario))
                radioGroup.addView(rb)
            }

            binding.containerPerguntas.addView(radioGroup, index * 2 + 1)

            radioGroups.add(radioGroup)
        }
    }

    private fun todasRespondidas(): Boolean {
        return radioGroups.all { it.checkedRadioButtonId != -1 }
    }

    private fun calcularPontuacao(): Int {
        var score = 0
        questions.forEachIndexed { index, question ->
            val radioGroup = radioGroups[index]
            val selectedId = radioGroup.checkedRadioButtonId
            val rb = findViewById<RadioButton>(selectedId) ?: return@forEachIndexed
            val indiceSelecionado = radioGroup.indexOfChild(rb)
            if (indiceSelecionado == question.correctAnswerIndex) {
                score += 10
            }
        }
        return score
    }
}