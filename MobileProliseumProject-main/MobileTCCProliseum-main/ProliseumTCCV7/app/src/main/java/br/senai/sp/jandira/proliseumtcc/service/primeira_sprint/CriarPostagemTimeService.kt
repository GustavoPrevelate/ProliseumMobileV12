package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.CreateJogadorProfile
import br.senai.sp.jandira.proliseumtcc.model.CriarPostagemTime
import br.senai.sp.jandira.proliseumtcc.model.CriarPostagemTimeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CriarPostagemTimeService {

    @POST("post")
    fun postCriarPostagemTime(@Header("Authorization") token: String, @Body criarPostagemTime: CriarPostagemTime): Call<CriarPostagemTimeResponse>
}