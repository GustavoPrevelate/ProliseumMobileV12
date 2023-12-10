package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.BodyTituloHighLight
import br.senai.sp.jandira.proliseumtcc.model.PeneiraResponse
import br.senai.sp.jandira.proliseumtcc.model.ResponseHighLight
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DeletarPeneiraService {

    @DELETE("sieve/{time}/{jogador}")
    fun deletarPeneira(
        @Header("Authorization") token: String,
        @Path("time") time: Int?,
        @Path("jogador") jogador: Int?,
        @Query("aceitar") aceitar: Int,
    ): Call<PeneiraResponse>
}