package br.senai.sp.jandira.proliseumtcc.model

data class GetPeneiraAceptedTime(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<GetPeneiraAceptedTimeJogadores>?,
    val propostas: List<GetPeneiraAceptedTimePropostas>?,
)
