package br.senai.sp.jandira.proliseumtcc.model

data class ResponsePostRedeSocialDonoPropostasDe(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<ResponsePostRedeSocialDonoPropostasDeJogadores>?,
    val propostas: List<ResponsePostRedeSocialDonoPropostasDePropostas>?,

    )