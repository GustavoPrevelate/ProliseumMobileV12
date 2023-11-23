package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GetMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.model.PutAtualizarMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.model.ResponsePutAtualizarMinhaPostagem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

interface PutMinhaPostagemService {

    @PUT("post")
    fun putMyPost(@Header("Authorization") token: String, @Body putAtualizarMinhaPostagem: PutAtualizarMinhaPostagem): Call<ResponsePutAtualizarMinhaPostagem>
}