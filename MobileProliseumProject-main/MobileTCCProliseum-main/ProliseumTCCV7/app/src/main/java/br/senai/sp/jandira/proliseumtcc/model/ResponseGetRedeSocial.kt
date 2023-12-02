package br.senai.sp.jandira.proliseumtcc.model

data class ResponseGetRedeSocial(
    val link: String?,
    val tipo: Int?,
    val dono: ResponseGetRedeSocialDono?,
    val id: Int?,
)
