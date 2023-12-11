package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.AGetTimeFilterByUser
import br.senai.sp.jandira.proliseumtcc.model.BodyTituloHighLight
import br.senai.sp.jandira.proliseumtcc.model.ResponseHighLight
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface AGetFilterTimesByUserService {

    @GET("team/user/{id}")
    fun getFilterTimesByUser(
        @Header("Authorization") token: String,
        @Path("id") id: Int?,
    ): Call<AGetTimeFilterByUser>
}