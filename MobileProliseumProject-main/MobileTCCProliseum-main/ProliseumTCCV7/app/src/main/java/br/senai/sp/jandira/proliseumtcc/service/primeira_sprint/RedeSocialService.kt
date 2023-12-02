package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilUsuario
import br.senai.sp.jandira.proliseumtcc.model.PostRedeSocial
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocial
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface RedeSocialService {

    @POST("network")
    fun criarRedeSocial(@Header("Authorization") token: String, @Body RedeSocial: PostRedeSocial): Call<ResponsePostRedeSocial>
}