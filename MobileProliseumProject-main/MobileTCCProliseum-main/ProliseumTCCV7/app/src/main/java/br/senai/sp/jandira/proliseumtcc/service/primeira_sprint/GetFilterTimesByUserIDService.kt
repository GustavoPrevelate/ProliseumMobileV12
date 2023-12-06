package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.GetFilterTimesByUserId
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetFilterTimesByUserIDService {

    @GET("team/user/{id}")
    fun getFilterTimesUserById(@Path("id") id: Int?): Call<GetFilterTimesByUserId>

}