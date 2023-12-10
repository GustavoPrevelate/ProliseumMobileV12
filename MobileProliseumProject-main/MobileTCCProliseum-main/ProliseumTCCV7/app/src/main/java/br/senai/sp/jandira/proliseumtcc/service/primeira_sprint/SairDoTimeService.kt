package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.SairDoTimeResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface SairDoTimeService {

    @PUT("player/leave")
    fun sairDoTime(
        @Header("Authorization") token: String,
    ): Call<SairDoTimeResponse>

}