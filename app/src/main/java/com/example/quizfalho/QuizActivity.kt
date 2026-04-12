package com.example.quizfalho

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizfalho.databinding.ActivityQuizBinding
import kotlin.collections.get
import kotlin.collections.plusAssign
import kotlin.compareTo
import kotlin.inc
import kotlin.text.compareTo
import kotlin.text.get

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private var questions: List<Question> = listOf()
    private var currentIndex = 0
    private var score = 0
    private var quizIniciado = false

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

        iniciarQuiz()

        binding.btnNext.setOnClickListener {
            processarResposta()
        }

        binding.btnVoltarLobby.setOnClickListener {
            finish()
        }
    }

    // 🔹 INICIA O QUIZ
    private fun iniciarQuiz() {
        quizIniciado = true

        binding.btnNext.text = "PRÓXIMA PERGUNTA"

        binding.optionsGroup.visibility = View.VISIBLE
        binding.txtQuestionNumber.visibility = View.VISIBLE
        binding.txtLiveScore.visibility = View.VISIBLE
        binding.btnVoltarLobby.visibility = View.GONE

        currentIndex = 0
        score = 0

        exibirPergunta()
    }

    override fun onBackPressed() {
        if (quizIniciado) {
            Toast.makeText(this, "Finalize o quiz primeiro!", Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
        }
    }

    // 🔹 RESPOSTAS
    private fun processarResposta() {
        val selectedId = binding.optionsGroup.checkedRadioButtonId

        if (selectedId != -1) {
            verificarResposta(selectedId)
            currentIndex++

            if (currentIndex < questions.size) {
                exibirPergunta()
            } else {
                mostrarTelaParabenizacao()
            }
        } else {
            Toast.makeText(this, "Selecione uma opção!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun exibirPergunta() {
        val q = questions[currentIndex]

        binding.txtQuestionNumber.text = "Questão ${currentIndex + 1}/${questions.size}"
        binding.txtQuestion.text = q.text

        binding.optionsGroup.clearCheck()
        binding.opt1.text = q.options[0]
        binding.opt2.text = q.options[1]
        binding.opt3.text = q.options[2]
        binding.opt4.text = q.options[3]
    }

    private fun verificarResposta(idSelecionado: Int) {
        val rbSelecionado = findViewById<RadioButton>(idSelecionado) ?: return

        val indiceSelecionado = binding.optionsGroup.indexOfChild(rbSelecionado)

        if (indiceSelecionado == questions[currentIndex].correctAnswerIndex) {
            score += 10
            binding.txtLiveScore.text = "$score 🏆"
        }
    }

    private fun mostrarTelaParabenizacao() {
        binding.optionsGroup.visibility = View.GONE
        binding.txtQuestionNumber.visibility = View.GONE
        binding.txtLiveScore.visibility = View.GONE
        binding.btnVoltarLobby.visibility = View.GONE

        binding.btnNext.text = "SAIR"
        binding.btnNext.setOnClickListener { finish() }

        val feedback = when {
            score <= 40 -> "Não desanime! Vamos tentar de novo? ✨"
            score <= 70 -> "Muito bem! Você teve um ótimo aproveitamento! 🚀"
            else -> "INCRÍVEL! Desempenho impecável! 👑"
        }

        binding.txtQuestion.text = "Fim do Quiz!\n\n$feedback\n\nPontos: $score"

    }
}