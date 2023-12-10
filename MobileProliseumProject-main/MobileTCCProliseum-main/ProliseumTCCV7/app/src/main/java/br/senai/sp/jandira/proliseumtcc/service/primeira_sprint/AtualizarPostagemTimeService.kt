package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.AtualizarPostagemTime
import br.senai.sp.jandira.proliseumtcc.model.AtualizarPostagemTimeResponse
import br.senai.sp.jandira.proliseumtcc.model.infoAtualizarTime
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AtualizarPostagemTimeService {

    @PUT("post{time}")
    fun atualizarPostagemTime(
        @Header("Authorization") token: String,
        @Query("time") time: Int,
        @Body atualizarPostagemTime: AtualizarPostagemTime
    ): Call<AtualizarPostagemTimeResponse>
}