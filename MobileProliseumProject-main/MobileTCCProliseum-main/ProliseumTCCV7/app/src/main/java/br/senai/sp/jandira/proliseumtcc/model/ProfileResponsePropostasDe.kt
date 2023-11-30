package br.senai.sp.jandira.proliseumtcc.model

data class ProfileResponsePropostasDe(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val jogadores: List<ProfileResponsePropostasDeJogadores>?,
    val propostas: List<ProfileResponsePropostasDePropostas>?,
    val redeSocial: List<ProfileResponsePropostasDeRedeSocial>?,
    val highlights: List<ProfileResponsePropostasDeHighlights>?,

)
