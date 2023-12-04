package br.senai.sp.jandira.proliseumtcc.model

data class GetProfileByIdPlayerProfileTimeAtual(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<GetProfileByIdPlayerProfileTimeAtualJogadores>?,
    val propostas: List<GetProfileByIdPlayerProfileTimeAtualPropostas>?,
)
