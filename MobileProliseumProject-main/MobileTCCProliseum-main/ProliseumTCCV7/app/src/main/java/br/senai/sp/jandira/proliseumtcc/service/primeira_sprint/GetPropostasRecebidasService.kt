package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.PropostasRecebidas
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header

interface GetPropostasRecebidasService {

    @GET("offer")
    fun getPropostasRecebidas(
        @Header("Authorization") token: String
    ): Call<PropostasRecebidas>
}