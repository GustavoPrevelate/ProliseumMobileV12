package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilUsuario
import br.senai.sp.jandira.proliseumtcc.model.ResponseFirstGetRedeSocial
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface GetRedeSocialService {

    @GET("network")
    fun getRedeSocial(@Header("Authorization") token: String): Call<ResponseFirstGetRedeSocial>
}