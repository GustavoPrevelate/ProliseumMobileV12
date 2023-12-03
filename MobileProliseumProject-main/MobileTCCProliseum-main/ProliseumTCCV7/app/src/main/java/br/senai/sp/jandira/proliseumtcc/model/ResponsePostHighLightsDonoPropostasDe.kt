package br.senai.sp.jandira.proliseumtcc.model

data class ResponsePostHighLightsDonoPropostasDe(
    val id: Int?,
    val nome_time: String?,
    val jogo: String?,
    val biografia: String?,
    val jogadores: List<ResponsePostHighLightsDonoPropostasDeJogadores>?,
    val propostas: List<ResponsePostHighLightsDonoPropostasDePropostas>?,


)
