package br.senai.sp.jandira.proliseumtcc.model

data class PropostasRecebidasGeralDe(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<PropostasRecebidasGeralDeJogadores>?,
    val propostas: List<PropostasRecebidasGeralDePropostas>?,
)
