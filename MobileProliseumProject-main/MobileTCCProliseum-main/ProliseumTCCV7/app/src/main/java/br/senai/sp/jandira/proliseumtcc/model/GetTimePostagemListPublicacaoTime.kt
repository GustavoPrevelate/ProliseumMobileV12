package br.senai.sp.jandira.proliseumtcc.model

data class GetTimePostagemListPublicacaoTime(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<GetTimePostagemListPublicacaoTimeJogadores>?,
    val propostas: List<GetTimePostagemListPublicacaoTimePropostas>?,
)
