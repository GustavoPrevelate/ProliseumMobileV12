package br.senai.sp.jandira.proliseumtcc.model

data class GetTimePostagemList(
    val post: List<GetTimePostagemListPublicacao>?,
    val limit: Int?,
)
