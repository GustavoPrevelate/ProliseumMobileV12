package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.BodyTituloHighLight
import br.senai.sp.jandira.proliseumtcc.model.BodyTituloHighLightComId
import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilUsuario
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLights
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface PostHighLightService {

    @POST("highlight")
    fun postHighlight(
        @Header("Authorization") token: String,
        @Body bodyTituloHighLight: BodyTituloHighLightComId
    ): Call<ResponsePostHighLights>

}