package br.senai.sp.jandira.proliseumtcc.model

data class GetProfileByIdUser(
    val id: Int?,
    val nome_usuario: String?,
    val nome_completo: String?,
    val email: String?,
    val data_nascimento: String?,
    val genero: Int?,
    val nickname: String?,
    val biografia: String?,
    val redeSocial: List<GetProfileByIdUserRedeSocial>?,
    val highlights: List<GetProfileByIdUserHighlights>?,
)
