package com.example.quizfalho

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizfalho.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityQuizBinding
    private var questions: List<Question> = listOf()
    private val checkBoxGroups = mutableListOf<List<CheckBox>>()

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
        setListeners()
    }

    private fun setListeners() {
        binding.btnEnviar.setOnClickListener(this)
        binding.btnVoltar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnEnviar -> {
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
            R.id.btnVoltar -> finish()
        }
    }

    private fun montarQuestionario() {
        questions.forEachIndexed { index, question ->

            val txtPergunta = TextView(this)
            txtPergunta.text = "${index + 1}. ${question.text}"
            txtPergunta.textSize = 18f
            txtPergunta.setTextColor(getColor(R.color.textoPrimario))
            txtPergunta.setPadding(0, 32, 0, 8)
            binding.containerPerguntas.addView(txtPergunta)

            val checkBoxList = mutableListOf<CheckBox>()
            val grupoLayout = LinearLayout(this)
            grupoLayout.orientation = LinearLayout.VERTICAL

            question.options.forEach { opcao ->
                val cb = CheckBox(this)
                cb.text = opcao
                cb.setTextColor(getColor(R.color.textoPrimario))
                grupoLayout.addView(cb)
                checkBoxList.add(cb)
            }

            binding.containerPerguntas.addView(grupoLayout)
            checkBoxGroups.add(checkBoxList)
        }
    }

    private fun todasRespondidas(): Boolean {
        return checkBoxGroups.all { grupo -> grupo.any { it.isChecked } }
    }

    private fun calcularPontuacao(): Int {
        var score = 0
        questions.forEachIndexed { index, question ->
            val grupo = checkBoxGroups[index]
            val indiceSelecionado = grupo.indexOfFirst { it.isChecked }
            if (indiceSelecionado == question.correctAnswerIndex) {
                score += 10
            }
        }
        return score
    }
}