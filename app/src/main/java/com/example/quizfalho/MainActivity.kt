    package com.example.quizfalho

    import android.content.Intent
    import android.os.Bundle
    import android.view.View
    import android.widget.Toast
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import com.example.quizfalho.databinding.ActivityMainBinding

    class MainActivity : AppCompatActivity(), View.OnClickListener {

        private lateinit var binding: ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            // Habilita o design de borda a borda
            // enableEdgeToEdge()

            // Inicializa o View Binding
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Teste para confirmar que a Activity iniciou
            Toast.makeText(this, "App abriu com sucesso!", Toast.LENGTH_LONG).show()

            // Configura as margens para as barras do sistema (status bar, navigation bar)
            //ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            //    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            //    insets
           // }

            val prefs = getSharedPreferences("quiz_prefs", MODE_PRIVATE)
            val ultimaPontuacao = prefs.getInt("ultima_pontuacao", 0)
            binding.txtUltimaPontuacao.text = "Última pontuação: $ultimaPontuacao"

            // Chama a função que configura os cliques
            setListeners()
        }

        private fun setListeners() {
            binding.cardHistoria.setOnClickListener(this)
            binding.cardCiencias.setOnClickListener(this)
            binding.cardEsportes.setOnClickListener(this)
            binding.cardArte.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(this, CategoryActivity::class.java)

            when (v?.id) {
                R.id.cardHistoria -> {
                    intent.putExtra("NOME_CATEGORIA", "Historia")
                    startActivity(intent)
                }
                R.id.cardCiencias -> {
                    intent.putExtra("NOME_CATEGORIA", "Ciencias")
                    startActivity(intent)
                }
                R.id.cardEsportes -> {
                    intent.putExtra("NOME_CATEGORIA", "Sports")
                    startActivity(intent)
                }
                R.id.cardArte -> {
                    intent.putExtra("NOME_CATEGORIA", "Art")
                    startActivity(intent)
                }
            }
        }
    }