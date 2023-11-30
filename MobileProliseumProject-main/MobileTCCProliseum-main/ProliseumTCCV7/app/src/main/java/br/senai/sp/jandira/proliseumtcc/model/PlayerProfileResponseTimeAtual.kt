package br.senai.sp.jandira.proliseumtcc.model

data class PlayerProfileResponseTimeAtual(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: Int?,
    val jogadores: List<PlayerProfileResponseTimeAtualJogadores>?,
    val propostas: List<PlayerProfileResponseTimeAtualPropostas>?
)
