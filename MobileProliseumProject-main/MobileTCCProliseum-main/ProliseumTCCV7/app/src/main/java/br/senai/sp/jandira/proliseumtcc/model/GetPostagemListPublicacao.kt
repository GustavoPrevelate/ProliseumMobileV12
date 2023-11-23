package br.senai.sp.jandira.proliseumtcc.model

data class GetPostagemListPublicacao(
    val id: Int?,
    val descricao: String?,
    val jogo: String?,
    val funcao: String?,
    val elo: String?,
    val hora: String?,
    val tipo: Boolean?,
    val pros: String?,
    val dono_id: GetPostagemListPublicacaoDonoId?,
)
