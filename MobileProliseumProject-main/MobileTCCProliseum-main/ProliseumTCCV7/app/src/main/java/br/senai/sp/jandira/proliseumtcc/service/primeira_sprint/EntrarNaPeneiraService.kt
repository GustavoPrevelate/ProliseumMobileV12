package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneira
import br.senai.sp.jandira.proliseumtcc.model.infoAtualizarTime
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface EntrarNaPeneiraService {

    @PUT("sieve/{time}")
    fun entrarNaPeneira(
        @Header("Authorization") token: String,
        @Path("time") id: Int,
    ): Call<EntrarNaPeneira>
}