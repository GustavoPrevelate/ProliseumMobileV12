package br.senai.sp.jandira.proliseumtcc.model

data class PostPropostaDe(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val dono: PostPropostaDeDono?,
    val jogadores: List<PostPropostaDeJogadores>?,
    val propostas: List<PostPropostaDePropostas>?,
)
