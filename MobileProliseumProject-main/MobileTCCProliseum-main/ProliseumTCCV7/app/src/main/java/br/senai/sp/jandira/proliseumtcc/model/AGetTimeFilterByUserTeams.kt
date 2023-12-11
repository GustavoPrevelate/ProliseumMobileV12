package br.senai.sp.jandira.proliseumtcc.model

data class AGetTimeFilterByUserTeams(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<AGetTimeFilterByUserTeamsJogadores>?,
    val propostas: List<AGetTimeFilterByUserTeamsPropostas>?,
)
