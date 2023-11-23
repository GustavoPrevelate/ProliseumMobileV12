package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GetPostagemList
import br.senai.sp.jandira.proliseumtcc.model.PostagemJogador
import br.senai.sp.jandira.proliseumtcc.model.PostagemJogadorResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GetPostagemListService {

    @GET("post/{tipo}")
    fun postagemJogadorService(
        @Path("tipo") tipo: Int?,
        @Query("page") page: Int?,
        @Query("perPage") perPage: Int?,
        @Query("hora") hora: String?,
        @Query("elo") elo: Int?,
        @Query("funcao") funcao: Int?,
    ): Call<GetPostagemList>
}