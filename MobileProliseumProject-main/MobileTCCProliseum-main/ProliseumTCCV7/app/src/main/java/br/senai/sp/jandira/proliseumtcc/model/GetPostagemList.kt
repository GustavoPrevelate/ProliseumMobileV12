package br.senai.sp.jandira.proliseumtcc.model

data class GetPostagemList(
    val post: List<GetPostagemListPublicacao>?,
    val limit: Int?,
)
