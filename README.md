# 🧠 ZereQuiz — Quiz de Cultura Geral
> Projeto Android desenvolvido em Kotlin para a disciplina de Desenvolvimento Mobile

---

## 📱 Sobre o App

O ZereQuiz é um app de quiz de cultura geral com quatro categorias temáticas. O usuário escolhe uma categoria, responde 10 perguntas e ao final recebe sua pontuação. Se acertar todas as 10, um banner especial de conquista é exibido. A última pontuação é salva no dispositivo e exibida na tela inicial.

---

## 🗺️ Fluxo de Telas

```
MainActivity
│   Logo + última pontuação + categorias em rolagem horizontal
│
├──▶ CategoryActivity
│       Imagem e nome da categoria + botão Começar + botão Voltar
│
├──▶ QuizActivity
│       10 perguntas em ScrollView
│       Cada pergunta tem 4 opções via CheckBox
│       Botão Enviar Respostas + botão Voltar
│
└──▶ ResultActivity
        Pontuação final exibida via Toast e na tela
        Banner de conquista (ImageView) se acertar 10/10
        Pontuação salva com SharedPreferences
        Botão Voltar ao Início
```

---

## ✅ Requisitos Obrigatórios — Todos Implementados

| Requisito | Onde é usado |
|---|---|
| `LinearLayout` | MainActivity, CategoryActivity, QuizActivity (estrutura base) |
| `ScrollView` | QuizActivity — perguntas roláveis |
| `HorizontalScrollView` | MainActivity — categorias em rolagem horizontal |
| `ImageView` | MainActivity (logo), CategoryActivity (imagem da categoria), ResultActivity (banner conquista) |
| `CheckBox` | QuizActivity — opções de resposta de cada pergunta |
| `View.OnClickListener` | Todas as Activities seguem o padrão com `setListeners()` e `onClick` |
| `Toast` | MainActivity (boas-vindas), QuizActivity (validação), ResultActivity (pontuação) |
| `Parâmetros entre Activities` | `NOME_CATEGORIA` flui de MainActivity → CategoryActivity → QuizActivity / `PONTUACAO` de QuizActivity → ResultActivity |
| `RelativeLayout` | ResultActivity — posiciona banner, pontuação e botão |
| `ViewBinding` | Todas as Activities usam ViewBinding (sem `findViewById`) |
| `SharedPreferences` | ResultActivity salva a pontuação / MainActivity lê e exibe |

---

## 🏗️ Estrutura do Projeto

```
app/src/main/
├── java/com/example/quizfalho/
│   ├── MainActivity.kt           ← tela inicial
│   ├── CategoryActivity.kt       ← sinopse da categoria
│   ├── QuizActivity.kt           ← perguntas e respostas
│   ├── ResultActivity.kt         ← resultado final
│   ├── Question.kt               ← modelo de dados da pergunta
│   └── QuestionRepository.kt     ← banco de perguntas (40 perguntas)
│
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── activity_category.xml
    │   ├── activity_quiz.xml
    │   └── activity_result.xml
    ├── drawable/                  ← logo, ícones de categoria, banner conquista
    └── values/
        ├── strings.xml
        ├── colors.xml
        └── themes.xml
```

---

## 🎨 Paleta de Cores

| Nome | Hex | Uso |
|---|---|---|
| `fundoPrincipal` | `#0F0E17` | Fundo de todas as telas |
| `surface` | `#1A1A2E` | Cards e containers |
| `destaque` | `#FFD60A` | Botões e elementos de destaque |
| `textoPrimario` | `#FFFFFE` | Texto principal |
| `textoSecundario` | `#A7A9BE` | Subtítulos e textos secundários |
| `acerto` | `#4CAF50` | Feedback positivo |
| `erro` | `#EF5350` | Feedback negativo |

---

## 📂 Categorias e Perguntas

O app tem 40 perguntas divididas em 4 categorias, 10 por categoria:

| Categoria | Chave interna | Temas abordados |
|---|---|---|
| História | `Historia` | Brasil, guerras, civilizações, revolução |
| Ciências | `Ciencias` | Astronomia, biologia, química, física |
| Esportes | `Sports` | Futebol, olimpíadas, atletismo, basquete |
| Arte | `Art` | Pintores, obras, museus, movimentos artísticos |

---

## 🔗 Passagem de Parâmetros entre Telas

```
MainActivity ──[NOME_CATEGORIA]──▶ CategoryActivity ──[NOME_CATEGORIA]──▶ QuizActivity
QuizActivity ──[PONTUACAO]──▶ ResultActivity
```

Enviar:
```kotlin
val intent = Intent(this, ProximaActivity::class.java)
intent.putExtra("NOME_CATEGORIA", "Historia")
startActivity(intent)
```

Receber:
```kotlin
val nomeCategoria = intent.getStringExtra("NOME_CATEGORIA")
```

---

## 💾 SharedPreferences

Salvar (ResultActivity):
```kotlin
val prefs = getSharedPreferences("quiz_prefs", MODE_PRIVATE)
prefs.edit().putInt("ultima_pontuacao", pontuacao).apply()
```

Ler (MainActivity):
```kotlin
val prefs = getSharedPreferences("quiz_prefs", MODE_PRIVATE)
val ultimaPontuacao = prefs.getInt("ultima_pontuacao", 0)
```

---

## 🔍 ViewBinding — Padrão Usado

Ativado no `build.gradle.kts`:
```kotlin
android {
    buildFeatures {
        viewBinding = true
    }
}
```

Usado em cada Activity:
```kotlin
private lateinit var binding: ActivityMainBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
}
```

---

## 🎯 Padrão do Professor — View.OnClickListener

Todas as Activities seguem esse padrão:

```kotlin
class MinhaActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        setListeners()
    }

    private fun setListeners() {
        binding.meuBotao.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.meuBotao -> { /* ação */ }
        }
    }
}
```

---

## 🚀 Como Rodar o Projeto

1. Clone o repositório:
```bash
git clone https://github.com/tiagosdbastos/QuizFalho.git
```

2. Abra no Android Studio → **Open** → selecione a pasta do projeto

3. Aguarde o Gradle sincronizar

4. Rode no emulador ou conecte um dispositivo físico via USB ou Wi-Fi (Android 11+)

---

## 🌿 Git — O que está versionado

**Sobe pro GitHub:**
- `app/src/` — todo o código e recursos
- `app/build.gradle.kts`
- `build.gradle.kts` da raiz
- `settings.gradle.kts`
- `gradle/libs.versions.toml`
- `gradle/wrapper/gradle-wrapper.properties`

**Não sobe (`.gitignore`):**
- `.gradle/` — cache de build
- `build/` — arquivos compilados
- `.idea/` — configurações locais do Android Studio
- `local.properties` — caminho do SDK na máquina local
- `**/.DS_Store` — arquivos ocultos do macOS

---

## 👥 Equipe e Divisão de Tarefas

| Membro | Responsabilidade |
|---|---|
| Tiago | `MainActivity` — tela inicial, HorizontalScrollView, navegação |
| Membro 2 | `CategoryActivity` — sinopse, imagem dinâmica, navegação |
| Membro 3 | `QuizActivity` — perguntas, CheckBox, cálculo de pontuação |
| Membro 4 | `ResultActivity` — pontuação, SharedPreferences, banner conquista |
| Membro 5 | `QuestionRepository` — banco de perguntas, integração geral |

---

## 📌 Convenções do Projeto

- Variáveis e funções: `camelCase`
- Arquivos de layout: `snake_case`
- Commits: português e descritivos (`feat: tela de resultado concluída`)
- Strings visíveis ao usuário: preferencialmente em `strings.xml`
- Nunca usar `var` quando a variável não é modificada — usar `val`

---

*Projeto desenvolvido com dedicação. Bora passar! 🎓*
