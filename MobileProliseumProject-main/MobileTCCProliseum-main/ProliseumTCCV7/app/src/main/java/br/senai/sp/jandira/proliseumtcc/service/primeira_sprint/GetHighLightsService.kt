package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.ResponseFirstGetHighLights
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GetHighLightsService {

    @GET("highlight")
    fun getHighLights(@Header("Authorization") token: String): Call<ResponseFirstGetHighLights>
}