package br.senai.sp.jandira.proliseumtcc.model

data class EntrarNaPeneiraAceptedJogadoresTimeAtual(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<EntrarNaPeneiraAceptedJogadoresTimeAtualJogadores>?,
    val propostas: List<EntrarNaPeneiraAceptedJogadoresTimeAtualPropostas>?,
)
