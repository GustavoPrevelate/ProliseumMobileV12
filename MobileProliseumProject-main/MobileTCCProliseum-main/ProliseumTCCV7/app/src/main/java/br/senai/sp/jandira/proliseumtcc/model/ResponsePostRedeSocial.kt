package br.senai.sp.jandira.proliseumtcc.model

data class ResponsePostRedeSocial(
    val link: String?,
    val tipo: Int?,
    val dono: ResponsePostRedeSocialDono?,
    val id: Int?,
)
