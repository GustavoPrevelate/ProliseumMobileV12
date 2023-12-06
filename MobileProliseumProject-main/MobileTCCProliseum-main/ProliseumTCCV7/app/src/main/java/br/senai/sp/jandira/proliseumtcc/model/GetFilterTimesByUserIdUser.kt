package br.senai.sp.jandira.proliseumtcc.model

data class GetFilterTimesByUserIdUser(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<GetFilterTimesByUserIdUserJogadores>?,
    val propostas: List<GetFilterTimesByUserIdUserPropostas>?,
)
