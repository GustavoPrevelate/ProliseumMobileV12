package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.GetMinhaPostagem
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


//GetMinhaPostagem
interface ApagarPublicacaoTimeService {

    @DELETE("post{time}")
    fun deletarMinhaPostagemTime(
        @Header("Authorization") token: String,
        @Query("time") time: String?
    ): Call<GenericResponse>
}



