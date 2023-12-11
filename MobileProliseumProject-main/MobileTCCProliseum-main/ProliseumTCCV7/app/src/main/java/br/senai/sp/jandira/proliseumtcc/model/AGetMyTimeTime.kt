package br.senai.sp.jandira.proliseumtcc.model

data class AGetMyTimeTime(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<AGetMyTimeTimeJogadores>?,
    val propostas: List<AGetMyTimeTimePropostas>?,
)
