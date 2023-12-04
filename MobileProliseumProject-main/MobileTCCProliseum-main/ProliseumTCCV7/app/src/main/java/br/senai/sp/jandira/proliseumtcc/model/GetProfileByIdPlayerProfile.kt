package br.senai.sp.jandira.proliseumtcc.model

data class GetProfileByIdPlayerProfile(
    val id: Int?,
    val nickname: String?,
    val jogo: Int?,
    val funcao: Int?,
    val elo: Int?,
    val time_atual: GetProfileByIdPlayerProfileTimeAtual?,
)
