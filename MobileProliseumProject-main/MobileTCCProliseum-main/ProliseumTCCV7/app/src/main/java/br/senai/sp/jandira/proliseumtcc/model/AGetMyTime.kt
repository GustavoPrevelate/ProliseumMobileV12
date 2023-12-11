package br.senai.sp.jandira.proliseumtcc.model

data class AGetMyTime(
    val user: AGetMyTimeUser?,
    val time: List<AGetMyTimeTime>?,
)
