package br.senai.sp.jandira.proliseumtcc.model

data class ResponseGetHighLights(
    val titulo: String?,
    val dono: ResponseGetHighLightsDono?,
    val id: Int?,
)
