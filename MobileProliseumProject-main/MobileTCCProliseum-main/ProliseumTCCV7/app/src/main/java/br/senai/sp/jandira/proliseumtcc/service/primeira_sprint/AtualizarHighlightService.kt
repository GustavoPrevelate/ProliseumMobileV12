package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.BodyTituloHighLight
import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilJogador
import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.ResponseHighLight
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface AtualizarHighlightService {

    @PUT("highlight/{id}")
    fun atualizarHighLight(
        @Header("Authorization") token: String,
        @Path("id") id: Int?,
        @Body titulo: BodyTituloHighLight,
    ): Call<ResponseHighLight>
}