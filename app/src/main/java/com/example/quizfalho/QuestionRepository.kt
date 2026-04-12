package com.example.quizfalho

object QuestionRepository {
    private val allQuestions = listOf(
        // HISTÓRIA (10)
        Question(1, "Quem foi o primeiro presidente do Brasil?", listOf("Deodoro da Fonseca", "Floriano Peixoto", "Vargas", "D. Pedro II"), 0, "Historia"),
        Question(2, "Em que ano começou a 2ª Guerra Mundial?", listOf("1914", "1939", "1945", "1930"), 1, "Historia"),
        Question(3, "Qual civilização construiu as Pirâmides de Gizé?", listOf("Maias", "Incas", "Egípcios", "Gregos"), 2, "Historia"),
        Question(4, "Quem descobriu o Brasil?", listOf("Vasco da Gama", "Cabral", "Colombo", "Magalhães"), 1, "Historia"),
        Question(5, "A Queda da Bastilha foi marco de qual revolução?", listOf("Industrial", "Americana", "Francesa", "Russa"), 2, "Historia"),
        Question(6, "Quem proclamou a independência do Brasil?", listOf("D. Pedro I", "D. Pedro II", "Tiradentes", "Zumbi"), 0, "Historia"),
        Question(7, "Onde nasceu a democracia?", listOf("Roma", "Egito", "Atenas", "Esparta"), 2, "Historia"),
        Question(8, "Qual era o nome da capital do Império Inca?", listOf("Tenochtitlán", "Machu Picchu", "Cuzco", "Lima"), 2, "Historia"),
        Question(9, "Quem foi o líder do movimento de direitos civis nos EUA?", listOf("Malcolm X", "Martin Luther King Jr.", "Obama", "Mandela"), 1, "Historia"),
        Question(10, "Em qual período viveu o homem das cavernas?", listOf("Paleolítico", "Neolítico", "Idade Média", "Moderna"), 0, "Historia"),

        // CIÊNCIAS (10)
        Question(11, "Qual é o planeta mais próximo do sol?", listOf("Terra", "Marte", "Mercúrio", "Vênus"), 2, "Ciencias"),
        Question(12, "Qual o maior mamífero do mundo?", listOf("Elefante", "Baleia Azul", "Tubarão", "Girafa"), 1, "Ciencias"),
        Question(13, "Qual gás os humanos exalam na respiração?", listOf("Oxigênio", "Nitrogênio", "Gás Carbônico", "Hélio"), 2, "Ciencias"),
        Question(14, "Quantos ossos tem um adulto (média)?", listOf("206", "300", "150", "100"), 0, "Ciencias"),
        Question(15, "Qual a fórmula química da água?", listOf("CO2", "H2O", "NaCl", "O2"), 1, "Ciencias"),
        Question(16, "Quem formulou a teoria da relatividade?", listOf("Newton", "Darwin", "Einstein", "Tesla"), 2, "Ciencias"),
        Question(17, "Qual é o satélite natural da Terra?", listOf("Sol", "Lua", "Marte", "Estrela"), 1, "Ciencias"),
        Question(18, "Onde fica o osso fêmur?", listOf("Braço", "Perna", "Costela", "Crânio"), 1, "Ciencias"),
        Question(19, "Qual órgão bombeia o sangue?", listOf("Pulmão", "Rim", "Coração", "Fígado"), 2, "Ciencias"),
        Question(20, "A fotossíntese é feita por quais seres?", listOf("Animais", "Fungos", "Plantas", "Vírus"), 2, "Ciencias"),

        // ESPORTES (10)
        Question(21, "Quantos jogadores tem um time de futebol em campo?", listOf("10", "11", "12", "9"), 1, "Sports"),
        Question(22, "Qual país ganhou mais Copas do Mundo?", listOf("Alemanha", "Itália", "Brasil", "Argentina"), 2, "Sports"),
        Question(23, "Onde surgiram os Jogos Olímpicos?", listOf("Roma", "Grécia", "França", "China"), 1, "Sports"),
        Question(24, "Quantos sets tem no máximo uma partida de vôlei?", listOf("3", "4", "5", "6"), 2, "Sports"),
        Question(25, "Quem é o Rei do Futebol?", listOf("Zico", "Maradona", "Pelé", "Messi"), 2, "Sports"),
        Question(26, "Qual esporte usa uma raquete e uma peteca?", listOf("Tênis", "Badminton", "Ping-pong", "Golfe"), 1, "Sports"),
        Question(27, "Qual a distância de uma maratona?", listOf("10km", "21km", "42km", "50km"), 2, "Sports"),
        Question(28, "Em que esporte se usa o 'Checkmate'?", listOf("Damas", "Xadrez", "Poker", "Gamão"), 1, "Sports"),
        Question(29, "Qual o tempo de duração de uma luta de boxe (round)?", listOf("2 min", "3 min", "5 min", "10 min"), 1, "Sports"),
        Question(30, "Quantos pontos vale uma cesta de basquete comum?", listOf("1", "2", "3", "5"), 1, "Sports"),

        // ARTE (10)
        Question(31, "Quem pintou 'A Última Ceia'?", listOf("Picasso", "Michelangelo", "Da Vinci", "Van Gogh"), 2, "Art"),
        Question(32, "Qual artista cortou a própria orelha?", listOf("Van Gogh", "Dalí", "Monet", "Renoir"), 0, "Art"),
        Question(33, "O 'Grito' é uma obra de qual pintor?", listOf("Munch", "Picasso", "Rembrandt", "Degas"), 0, "Art"),
        Question(34, "Qual movimento artístico usava formas geométricas?", listOf("Barroco", "Realismo", "Cubismo", "Rococó"), 2, "Art"),
        Question(35, "Quem esculpiu 'Davi'?", listOf("Donatello", "Bernini", "Michelangelo", "Rodin"), 2, "Art"),
        Question(36, "A Monalisa está em qual museu?", listOf("British Museum", "Louvre", "Metropolitan", "Prado"), 1, "Art"),
        Question(37, "Qual artista é famosa por seus autorretratos e sobrancelhas?", listOf("Tarsila", "Frida Kahlo", "Anita Malfatti", "Lygia Clark"), 1, "Art"),
        Question(38, "Quem é o autor de 'Guernica'?", listOf("Dalí", "Picasso", "Miró", "Velázquez"), 1, "Art"),
        Question(39, "Qual técnica usa pontos para criar imagens?", listOf("Pontilhismo", "Surrealismo", "Aquarela", "Afresco"), 0, "Art"),
        Question(40, "Quem pintou o teto da Capela Sistina?", listOf("Rafael", "Leonardo", "Michelangelo", "Bramante"), 2, "Art")
    )

    fun getQuestionsByCategory(category: String): List<Question> {
        return allQuestions.filter { it.category == category }
    }
}