package br.senai.sp.jandira.proliseumtcc.model

data class EntrarNaPeneiraAcepted(
    val id: Int?,
    val menssagem: String?,
    val jogadores: List<EntrarNaPeneiraAceptedJogadores>?,
)
