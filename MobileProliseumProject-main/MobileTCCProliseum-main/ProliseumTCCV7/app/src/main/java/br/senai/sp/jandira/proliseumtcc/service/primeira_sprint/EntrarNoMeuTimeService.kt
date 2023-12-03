package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.AddOrganizadorAOTime
import br.senai.sp.jandira.proliseumtcc.model.AdicionarJogadorAoTime
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface EntrarNoMeuTimeService {

    @PUT("team/{time}/{jogador}")
    fun putTeamTimeJogador(
        @Header("Authorization") token: String,
        @Path("time") time: Int?,
        @Path("jogador") id: Int?
    ): Call<AdicionarJogadorAoTime>
}