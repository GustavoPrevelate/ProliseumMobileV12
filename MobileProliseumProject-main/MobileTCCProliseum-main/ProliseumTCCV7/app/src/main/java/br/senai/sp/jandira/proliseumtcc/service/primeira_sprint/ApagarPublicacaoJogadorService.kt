package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.GetMinhaPostagem
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header


//GetMinhaPostagem
interface ApagarPublicacaoJogadorService {

    @DELETE("post")
    fun deletarMinhaPostagem(
        @Header("Authorization") token: String
    ): Call<GenericResponse>
}



