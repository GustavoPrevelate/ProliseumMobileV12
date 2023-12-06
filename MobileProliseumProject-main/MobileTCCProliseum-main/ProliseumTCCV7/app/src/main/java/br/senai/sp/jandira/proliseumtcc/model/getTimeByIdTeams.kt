package br.senai.sp.jandira.proliseumtcc.model

data class getTimeByIdTeams(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val dono: getTimeByIdTeamsOrganizacaoDonoId?,
    val jogadores: List<getTimeByIdTeamsJogadores>?,
    val propostas: List<getTimeByIdTeamsPropostas>?
)
