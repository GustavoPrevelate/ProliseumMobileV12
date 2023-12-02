package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.Path

interface DeletarRedeSocialService {

    @DELETE("network/{id}")
    fun deleteRedeSocial(
        @Header("Authorization") token: String,
        @Path("id") id: Int?
    ): Call<GenericResponse>
}