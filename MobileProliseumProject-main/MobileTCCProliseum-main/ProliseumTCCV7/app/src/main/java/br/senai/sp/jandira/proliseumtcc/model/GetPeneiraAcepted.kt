package br.senai.sp.jandira.proliseumtcc.model

data class GetPeneiraAcepted(
    val id: Int?,
    val menssagem: String?,
    val time: GetPeneiraAceptedTime?,
    val jogadores: List<GetPeneiraAceptedJogadores>?,
)
