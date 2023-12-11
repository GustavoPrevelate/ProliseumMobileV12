package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.AGetMyTime
import br.senai.sp.jandira.proliseumtcc.model.infoAtualizarTime
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface AGetMyTimeService {

    @GET("team/myteams}")
    fun aGetMyTime(
        @Header("Authorization") token: String,
    ): Call<AGetMyTime>
}