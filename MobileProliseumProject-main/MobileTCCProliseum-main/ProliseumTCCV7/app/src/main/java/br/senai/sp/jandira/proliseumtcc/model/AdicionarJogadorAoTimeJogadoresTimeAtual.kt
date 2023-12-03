package br.senai.sp.jandira.proliseumtcc.model

data class AdicionarJogadorAoTimeJogadoresTimeAtual(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<AdicionarJogadorAoTimeJogadoresTimeAtualJogadores>?,
    val propostas: List<AdicionarJogadorAoTimeJogadoresTimeAtualJogadoresPropostas>?,
)
