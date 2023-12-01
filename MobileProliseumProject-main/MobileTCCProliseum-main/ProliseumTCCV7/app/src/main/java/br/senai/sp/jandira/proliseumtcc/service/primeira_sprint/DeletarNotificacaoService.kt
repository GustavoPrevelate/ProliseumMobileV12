package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.Notificacao
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DeletarNotificacaoService {

    @DELETE("notification/{id}")
    fun deleteNotificacao(
        @Header("Authorization") token: String,
        @Path("id") id: Int?
    ): Call<GenericResponse>
}