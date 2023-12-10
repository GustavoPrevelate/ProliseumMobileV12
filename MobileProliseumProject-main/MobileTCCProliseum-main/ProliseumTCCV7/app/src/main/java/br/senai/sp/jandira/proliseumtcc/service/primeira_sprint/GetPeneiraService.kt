package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.GetPeneira
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GetPeneiraService {

    @GET("sieve/{time}")
    fun getPeneiraPostagemTime(
        @Header("Authorization") token: String,
        @Path("time") time: Int?
    ): Call<GetPeneira>
}