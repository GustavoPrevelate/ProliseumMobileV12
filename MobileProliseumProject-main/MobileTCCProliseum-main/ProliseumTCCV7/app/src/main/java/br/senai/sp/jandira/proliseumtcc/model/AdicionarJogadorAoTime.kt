package br.senai.sp.jandira.proliseumtcc.model

data class AdicionarJogadorAoTime(
    val id: Int?,
    val nome_time: String?,
    val jogo: Int?,
    val biografia: String?,
    val dono: AdicionarJogadorAoTimeDono?,
    val jogadores: List<AdicionarJogadorAoTimeJogadores>?,
    val propostas: List<AdicionarJogadorAoTimePropostas>?

)
