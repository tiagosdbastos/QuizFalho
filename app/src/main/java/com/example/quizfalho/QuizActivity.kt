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
        // Da um indice pra cada pergunta e a partir dai vai criando o resto
        questions.forEachIndexed { index, question ->

            // Cria um TextView para exibir o enunciado da pergunta
            val txtPergunta = TextView(this)
            txtPergunta.text = "${index + 1}. ${question.text}"
            txtPergunta.textSize = 18f
            txtPergunta.setTextColor(getColor(R.color.textoPrimario))
            txtPergunta.setPadding(0, 32, 0, 8) // Padding na parte superior pra separar as perguntas
            binding.containerPerguntas.addView(txtPergunta)


            val checkBoxList = mutableListOf<CheckBox>() // Lista temporária que armazena os CheckBoxes das alternativas
            val grupoLayout = LinearLayout(this) // Layout vertical que agrupa todos os CheckBoxes de uma mesma pergunta
            grupoLayout.orientation = LinearLayout.VERTICAL

            question.options.forEach { opcao -> // Cria um CheckBox para cada alternativa e o adiciona ao grupo desta pergunta
                val cb = CheckBox(this)
                cb.text = opcao
                cb.setTextColor(getColor(R.color.textoPrimario))
                grupoLayout.addView(cb) // adiciona o CheckBox ao LinearLayout vertical
                checkBoxList.add(cb)  // registra o CheckBox para verificação
            }

            binding.containerPerguntas.addView(grupoLayout)  // Adiciona o grupo de alternativas ao container principal da tela
            checkBoxGroups.add(checkBoxList) // Salva a lista de CheckBoxes desta pergunta no índice correspondente.
        }
    }

    private fun todasRespondidas(): Boolean { //Verifica se todas as perguntas foram respondidas
        return checkBoxGroups.all { grupo -> grupo.any { it.isChecked } }
    }

    private fun calcularPontuacao(): Int {
        var score = 0
        questions.forEachIndexed { index, question ->
            val grupo = checkBoxGroups[index]
            val indiceSelecionado = grupo.indexOfFirst { it.isChecked } // Obtém o índice do primeiro CheckBox marcado no grupo desta pergunta
            if (indiceSelecionado == question.correctAnswerIndex) { // Faz a comparacao do indice pra confirmar a resposta
                score += 10
            }
        }
        return score
    }
}