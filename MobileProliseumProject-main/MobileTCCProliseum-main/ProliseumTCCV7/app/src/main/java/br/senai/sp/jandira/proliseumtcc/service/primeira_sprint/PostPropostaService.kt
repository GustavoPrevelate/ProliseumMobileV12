package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.BodyPostProposta
import br.senai.sp.jandira.proliseumtcc.model.BodyTituloHighLight
import br.senai.sp.jandira.proliseumtcc.model.PostProposta
import br.senai.sp.jandira.proliseumtcc.model.ResponseHighLight
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PostPropostaService {
    @POST("offer/{Time}/{Jogador}")
    fun criarProposta(
        @Header("Authorization") token: String,
        @Path("Time") timeID: Int?,
        @Path("Jogador") jogadorID: Int?,
        @Body menssagem: BodyPostProposta,
    ): Call<PostProposta>
}