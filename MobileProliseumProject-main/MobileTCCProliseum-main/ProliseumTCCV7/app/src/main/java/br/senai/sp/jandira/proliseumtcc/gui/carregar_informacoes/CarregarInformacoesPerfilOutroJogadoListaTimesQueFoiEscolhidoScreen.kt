package br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import br.senai.sp.jandira.proliseumtcc.model.GetProfileByIdDoUsuario
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresDentroDeTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresDentroDeTimeList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresInfoPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresPropostasList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresPropostasRecebidas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelNomeJogadorListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdDoUsuario
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUserHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUserRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesPerfilOutroJogadorListaTimesQueFoiEscolhidoScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    // SharedViewModel GET TIME FILTER
    sharedGetTime: SharedGetTime,
    sharedGetTimeTeams: SharedGetTimeTeams,
    sharedGetTimeTeamsJogadores: SharedGetTimeTeamsJogadores,
    sharedGetTimeTeamsJogadoresPerfilId: SharedGetTimeTeamsJogadoresPerfilId,
    sharedGetTimeDono: SharedGetTimeDono,
    sharedGetTimeTeamsPropostas: SharedGetTimeTeamsPropostas,

    sharedGetProfileByIdDoUsuario: SharedGetProfileByIdDoUsuario,
    sharedGetProfileByIdUser: SharedGetProfileByIdUser,
    sharedGetProfileByIdUserRedeSocial: SharedGetProfileByIdUserRedeSocial,
    sharedGetProfileByIdUserHighlights: SharedGetProfileByIdUserHighlights,

    sharedGetProfileByIdPlayerProfile: SharedGetProfileByIdPlayerProfile,
    sharedGetProfileByIdPlayerProfileTimeAtual: SharedGetProfileByIdPlayerProfileTimeAtual,
    sharedGetProfileByIdPlayerProfileTimeAtualJogadores: SharedGetProfileByIdPlayerProfileTimeAtualJogadores,
    sharedGetProfileByIdPlayerProfileTimeAtualPropostas: SharedGetProfileByIdPlayerProfileTimeAtualPropostas,

    sharedGetTimeByIdTeamsJogadoresPerfilId: SharedGetTimeByIdTeamsJogadoresPerfilId,


    onNavigate: (String) -> Unit
) {

    //CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 3 segundos antes de continuar
        delay(3000)
        loading = false
    }

    //DESIGN DA TELA
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        AzulEscuroProliseum, AzulEscuroProliseum
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {


            if (loading) {
                // Exibe um indicador de progresso enquanto carrega
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )

                // Se o tempo de espera terminou, continue com a validação do token
                // Restante do código aqui
                val token = sharedViewModelTokenEId.token
                // Restante do seu código de validação do token
                Log.d("CarrregarPerfilOutroJogadorListaTimesScreen", "Token: $token")

                val idOutroJogador = sharedGetTimeByIdTeamsJogadoresPerfilId.id

                Log.d("CarrregarPerfilOutroJogadorListaTimesScreen", "id do jogador escolhido: $idOutroJogador")

                if(token != null && token.isNotEmpty()){

                    val profileOutroJogadorService = RetrofitFactoryCadastro().getJogadoresByIdService()

                    profileOutroJogadorService.getProfileById(idOutroJogador).enqueue(object : Callback<GetProfileByIdDoUsuario> {
                        override fun onResponse(call: Call<GetProfileByIdDoUsuario>, response: Response<GetProfileByIdDoUsuario>) {
                            if (response.isSuccessful) {
                                Log.d("CarrregarPerfilOutroJogadorListaTimesScreen", "CarrregarPerfilOutroJogadorListaTimesScreen, Resposta bem-sucedida: ${response.code()}")

                                val getUserByIdResponse = response.body()

                                val user = getUserByIdResponse?.user
                                val playerProfile = getUserByIdResponse?.playerProfile

                                sharedGetProfileByIdDoUsuario.user = getUserByIdResponse?.user
                                sharedGetProfileByIdDoUsuario.playerProfile = getUserByIdResponse?.playerProfile



                                if(user != null){

                                    val idInfoUserGetById = user.id
                                    val nomeUsuarioInfoUserGetById = user.nome_usuario
                                    val nomeCompletoInfoUserGetById = user.nome_completo
                                    val emailInfoUserGetById = user.email
                                    val dataNascimentoInfoUserGetById = user.data_nascimento
                                    val generoInfoUserGetById = user.genero
                                    val nicknameInfoUserGetById = user.nickname
                                    val biografiaInfoUserGetById = user.biografia

                                    sharedGetProfileByIdUser.id = user.id
                                    sharedGetProfileByIdUser.nome_usuario = user.nome_usuario
                                    sharedGetProfileByIdUser.nome_completo = user.nome_completo
                                    sharedGetProfileByIdUser.email = user.email
                                    sharedGetProfileByIdUser.data_nascimento = user.data_nascimento
                                    sharedGetProfileByIdUser.genero = user.genero
                                    sharedGetProfileByIdUser.nickname = user.nickname
                                    sharedGetProfileByIdUser.biografia = user.biografia

                                    val redeSocialInfoUserGetById = user.redeSocial
                                    val highlightsInfoUserGetById = user.highlights

                                    sharedGetProfileByIdUser.redeSocial = user.redeSocial
                                    sharedGetProfileByIdUser.highlights = user.highlights

                                    if(redeSocialInfoUserGetById != null){
                                        for(redeSocialInfo in redeSocialInfoUserGetById){
                                            val idRedeSocialInfoUserGetById = redeSocialInfo.id
                                            val linkRedeSocialInfoUserGetById = redeSocialInfo.link
                                            val tipoRedeSocialInfoUserGetById = redeSocialInfo.tipo

                                            sharedGetProfileByIdUserRedeSocial.id = redeSocialInfo.id
                                            sharedGetProfileByIdUserRedeSocial.link = redeSocialInfo.link
                                            sharedGetProfileByIdUserRedeSocial.tipo = redeSocialInfo.tipo
                                        }
                                    }

                                    if(highlightsInfoUserGetById != null){
                                        for(highLightsInfo in highlightsInfoUserGetById){
                                            val idHighlightInfoUserGetById = highLightsInfo.id
                                            val tituloHighlightInfoUserGetById = highLightsInfo.titulo

                                            sharedGetProfileByIdUserHighlights.id = highLightsInfo.id
                                            sharedGetProfileByIdUserHighlights.titulo = highLightsInfo.titulo
                                        }
                                    }
                                }

                                if(playerProfile != null){
                                    val idInfoPlayerProfileGetById = playerProfile.id
                                    val nickNameInfoPlayerProfileGetById = playerProfile.nickname
                                    val jogoInfoPlayerProfileGetById = playerProfile.jogo
                                    val funcaoInfoPlayerProfileGetById = playerProfile.funcao
                                    val eloInfoPlayerProfileGetById = playerProfile.elo

                                    sharedGetProfileByIdPlayerProfile.id = playerProfile.id
                                    sharedGetProfileByIdPlayerProfile.nickname = playerProfile.nickname
                                    sharedGetProfileByIdPlayerProfile.jogo = playerProfile.jogo
                                    sharedGetProfileByIdPlayerProfile.funcao = playerProfile.funcao
                                    sharedGetProfileByIdPlayerProfile.elo = playerProfile.elo

                                    val timeAtualInfoPlayerProfileGetById = playerProfile.time_atual

                                    sharedGetProfileByIdPlayerProfile.time_atual = playerProfile.time_atual

                                    if(timeAtualInfoPlayerProfileGetById != null){
                                        val idTimeAtualInfoPlayerProfileGetById = timeAtualInfoPlayerProfileGetById.id
                                        val nomeTimeTimeAtualInfoPlayerProfileGetById = timeAtualInfoPlayerProfileGetById.nome_time
                                        val jogoTimeAtualInfoPlayerProfileGetById = timeAtualInfoPlayerProfileGetById.jogo
                                        val biografiaTimeAtualInfoPlayerProfileGetById = timeAtualInfoPlayerProfileGetById.biografia

                                        sharedGetProfileByIdPlayerProfileTimeAtual.id = timeAtualInfoPlayerProfileGetById.id
                                        sharedGetProfileByIdPlayerProfileTimeAtual.nome_time = timeAtualInfoPlayerProfileGetById.nome_time
                                        sharedGetProfileByIdPlayerProfileTimeAtual.jogo = timeAtualInfoPlayerProfileGetById.jogo
                                        sharedGetProfileByIdPlayerProfileTimeAtual.biografia = timeAtualInfoPlayerProfileGetById.biografia



                                        val jogadoresTimeAtualInfoPlayerProfileGetById = timeAtualInfoPlayerProfileGetById.jogadores

                                        sharedGetProfileByIdPlayerProfileTimeAtual.jogadores = timeAtualInfoPlayerProfileGetById.jogadores

                                        if(jogadoresTimeAtualInfoPlayerProfileGetById != null){
                                            for(jogadoresTimeAtualInfo in jogadoresTimeAtualInfoPlayerProfileGetById){
                                                val idJogadorTimeAtualPlayerProfile = jogadoresTimeAtualInfo.id
                                                val nicknameJogadorTimeAtualPlayerProfile = jogadoresTimeAtualInfo.nickname
                                                val jogoJogadorTimeAtualPlayerProfile = jogadoresTimeAtualInfo.jogo
                                                val funcaoJogadorTimeAtualPlayerProfile = jogadoresTimeAtualInfo.funcao
                                                val eloJogadorTimeAtualPlayerProfile = jogadoresTimeAtualInfo.elo

                                                sharedGetProfileByIdPlayerProfileTimeAtualJogadores.id = jogadoresTimeAtualInfo.id
                                                sharedGetProfileByIdPlayerProfileTimeAtualJogadores.nickname = jogadoresTimeAtualInfo.nickname
                                                sharedGetProfileByIdPlayerProfileTimeAtualJogadores.jogo = jogadoresTimeAtualInfo.jogo
                                                sharedGetProfileByIdPlayerProfileTimeAtualJogadores.funcao = jogadoresTimeAtualInfo.funcao
                                                sharedGetProfileByIdPlayerProfileTimeAtualJogadores.elo = jogadoresTimeAtualInfo.elo
                                            }
                                        }

                                        val propostasTimeAtualInfoPlayerProfileGetById = timeAtualInfoPlayerProfileGetById.propostas

                                        sharedGetProfileByIdPlayerProfileTimeAtual.propostas = timeAtualInfoPlayerProfileGetById.propostas

                                        if(propostasTimeAtualInfoPlayerProfileGetById != null){
                                            for(propostasTimeAtualInfo in propostasTimeAtualInfoPlayerProfileGetById){
                                                val idPropostasTimeAtualPlayerProfile = propostasTimeAtualInfo.id
                                                val menssagemPropostasTimeAtualPlayerProfile = propostasTimeAtualInfo.menssagem

                                                sharedGetProfileByIdPlayerProfileTimeAtualPropostas.id = propostasTimeAtualInfo.id
                                                sharedGetProfileByIdPlayerProfileTimeAtualPropostas.menssagem = propostasTimeAtualInfo.menssagem
                                            }
                                        }
                                    }

                                }

                                onNavigate("perfil_de_outro_jogador_lista_jogadores")



                            } else {
                                // Trate a resposta não bem-sucedida
                                Log.d("CarrregarPerfilOutroJogadorListaTimesScreen", "CarrregarPerfilOutroJogadorListaTimesScreen, Resposta não bem-sucedida: ${response.code()}")
                                // Log de corpo da resposta (se necessário)
                                Log.d(
                                    "CarrregarPerfilOutroJogadorListaTimesScreen",
                                    "Corpo da resposta: ${response.errorBody()?.string()}"
                                )
                            }
                        }

                        override fun onFailure(call: Call<GetProfileByIdDoUsuario>, t: Throwable) {
                            // Trate o erro de falha na rede.
                            Log.d("CarrregarPerfilOutroJogadorListaTimesScreen", "Erro de rede: ${t.message}")
                        }

                    })

                } else{
                    Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(),
                        color = RedProliseum
                    )
                }
            }


        }
    }

}