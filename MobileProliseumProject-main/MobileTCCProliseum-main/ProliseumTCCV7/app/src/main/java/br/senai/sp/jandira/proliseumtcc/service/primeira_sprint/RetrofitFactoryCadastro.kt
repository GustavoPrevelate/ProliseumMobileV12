package br.senai.sp.jandira.proliseumtcc.service.primeira_sprint

import br.senai.sp.jandira.proliseumtcc.model.getTimeById
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitFactoryCadastro {

    private val URL_BASE = "https://proliseum-back.cyclic.app"

    //Guarda a conexão com o servidor, devolve a conexão
    private val retrofitFactoryCadastro = Retrofit
        .Builder()
        .baseUrl(URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getRetrofitFactoryCadastro(): Retrofit {
        return retrofitFactoryCadastro
    }

    fun postLoginService(): Retrofit{
        return retrofitFactoryCadastro
    }

    // Adicione um método para obter o serviço PerfilUsuarioService com o token
    fun getPerfilUsuarioService(): ProfileService {
//        val client = OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                // Adicione o token de autorização ao cabeçalho de cada solicitação
//                val request = chain.request()
//                    .newBuilder()
//                    .build()
//                chain.proceed(request)
//            }
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(URL_BASE)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)  // Use o cliente com o token no cabeçalho
//            .build()

        return retrofitFactoryCadastro.create(ProfileService::class.java)

    }

    fun putEditarPerfilUsuarioService(): EditarPerfilUsuarioService {

        return retrofitFactoryCadastro.create(EditarPerfilUsuarioService::class.java)
    }

    fun createJogadorProfileService(): CreateJogadorProfileService{
        return retrofitFactoryCadastro.create(CreateJogadorProfileService::class.java)
    }

    fun createOrganizacaoProfileService(): CreateOrganizacaoProfileService{
        return retrofitFactoryCadastro.create(CreateOrganizacaoProfileService::class.java)
    }

    fun putEditarPerfilOrganizacaoService(): EditarPerfilUsuarioOrganizacaoService {

        return retrofitFactoryCadastro.create(EditarPerfilUsuarioOrganizacaoService::class.java)
    }

    fun putEditarPerfilJogadorService(): EditarPerfilUsuarioJogadorService {

        return retrofitFactoryCadastro.create(EditarPerfilUsuarioJogadorService::class.java)
    }

    fun deletarOrganizadorService(): DeleteOrganizacaoService {

        return retrofitFactoryCadastro.create(DeleteOrganizacaoService::class.java)
    }

    fun createTimeService(): CreateTimeService {
        return retrofitFactoryCadastro.create(CreateTimeService::class.java)
    }

    fun getMyTeamsService(): MeusTimesService {
        return retrofitFactoryCadastro.create(MeusTimesService::class.java)
    }

    fun postUpdateTimeService(): AtualizarInfoTimeService {
        return retrofitFactoryCadastro.create(AtualizarInfoTimeService::class.java)
    }

    fun getJogadoresService(): GetJogadoresService {
        return retrofitFactoryCadastro.create(GetJogadoresService::class.java)
    }

    fun getJogadoresByIdService(): GetProfileById {
        return retrofitFactoryCadastro.create(GetProfileById::class.java)
    }
    fun theGetTimeByIdService(): GetTimeById {
        return retrofitFactoryCadastro.create(GetTimeById::class.java)
    }

    fun theGetTimeService(): GetTime {
        return retrofitFactoryCadastro.create(GetTime::class.java)
    }

    fun EntrarNoMeuTimeService(): EntrarNoMeuTimeService {
        return retrofitFactoryCadastro.create(EntrarNoMeuTimeService::class.java)
    }

    fun postagemJogadorService(): PostagemJogadorService {
        return retrofitFactoryCadastro.create(PostagemJogadorService::class.java)
    }

    fun getPostagemListService(): GetPostagemListService {
        return retrofitFactoryCadastro.create(GetPostagemListService::class.java)
    }

    fun getMinhaPostagemService(): GetMinhaPostagemService {
        return retrofitFactoryCadastro.create(GetMinhaPostagemService::class.java)
    }

    fun putMinhaPostagemService(): PutMinhaPostagemService {
        return retrofitFactoryCadastro.create(PutMinhaPostagemService::class.java)
    }

    fun notificacaoService(): NotificacaoService {
        return retrofitFactoryCadastro.create(NotificacaoService::class.java)
    }

    fun deletarNotificacaoService(): DeletarNotificacaoService {
        return retrofitFactoryCadastro.create(DeletarNotificacaoService::class.java)
    }

    fun redeSocialService(): RedeSocialService {
        return retrofitFactoryCadastro.create(RedeSocialService::class.java)
    }

    fun getRedeSocialService(): GetRedeSocialService {
        return retrofitFactoryCadastro.create(GetRedeSocialService::class.java)
    }

    fun deletarRedeSocialService(): DeletarRedeSocialService {
        return retrofitFactoryCadastro.create(DeletarRedeSocialService::class.java)
    }

    fun getHighLightsService(): GetHighLightsService {
        return retrofitFactoryCadastro.create(GetHighLightsService::class.java)
    }

    fun atualizarHighlightService(): AtualizarHighlightService {
        return retrofitFactoryCadastro.create(AtualizarHighlightService::class.java)
    }

    fun deletarHighLightService(): DeletarHighLightService {
        return retrofitFactoryCadastro.create(DeletarHighLightService::class.java)
    }

    fun postHighLightService(): PostHighLightService {
        return retrofitFactoryCadastro.create(PostHighLightService::class.java)
    }


}