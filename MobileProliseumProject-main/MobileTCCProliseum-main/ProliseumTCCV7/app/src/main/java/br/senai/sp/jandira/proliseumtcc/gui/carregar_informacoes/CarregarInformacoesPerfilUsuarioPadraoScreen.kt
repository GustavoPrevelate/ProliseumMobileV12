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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelUser
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesPerfilUsuarioPadraoScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    sharedViewModelPerfil: SharedViewModelPerfil,
    sharedViewModelUser: SharedViewModelUser,
    sharedViewModelPerfilPropostas: SharedViewModelPerfilPropostas,
    sharedViewModelPerfilPropostasDe: SharedViewModelPerfilPropostasDe,
    sharedViewModelPerfilPropostasDeJogadores: SharedViewModelPerfilPropostasDeJogadores,
    sharedViewModelPerfilPropostasDePropostas: SharedViewModelPerfilPropostasDePropostas,

    sharedViewModelPlayerProfile: SharedViewModelPlayerProfile,
    sharedViewModelPlayerProfileTimeAtual: SharedViewModelPlayerProfileTimeAtual,
    sharedViewModelPlayerProfileTimeAtualJogadores: SharedViewModelPlayerProfileTimeAtualJogadores,
    sharedViewModelPlayerProfileTimeAtualPropostas: SharedViewModelPlayerProfileTimeAtualPropostas,

    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,
    onNavigate: (String) -> Unit
) {

    //CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 3 segundos antes de continuar
        delay(3000)
        loading = false
    }

    // DESIGN DA TELA
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

                //TOKEN
                val token = sharedViewModelTokenEId.token

                if(token != null && token.isNotEmpty()){

                    val profileService = RetrofitFactoryCadastro().getPerfilUsuarioService()

                    profileService.getProfile("Bearer " + token).enqueue(object : Callback<ProfileResponse> {
                        override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                            if (response.isSuccessful) {
                                Log.d("PerfilUsuarioJogadorScreen", "Resposta bem-sucedida: ${response.code()}")
                                val profileResponseData = response.body()

                                val user = profileResponseData?.user
                                val playerProfile = profileResponseData?.playerProfile

                                sharedViewModelPerfil.user = profileResponseData?.user
                                sharedViewModelPerfil.playerProfile = profileResponseData?.playerProfile


                                if(user != null){
                                    sharedViewModelUser.id = user.id
                                    sharedViewModelUser.nome_usuario = user.nome_usuario
                                    sharedViewModelUser.nome_completo = user.nome_completo
                                    sharedViewModelUser.email = user.email
                                    sharedViewModelUser.data_nascimento = user.data_nascimento
                                    sharedViewModelUser.genero = user.genero
                                    sharedViewModelUser.nickname = user.nickname
                                    sharedViewModelUser.biografia = user.biografia

                                    sharedViewModelUser.propostas = user.propostas

                                    val userPropostas = user.propostas

                                    if(userPropostas != null){
                                        for (userProposta in userPropostas){
                                            sharedViewModelPerfilPropostas.id = userProposta.id
                                            sharedViewModelPerfilPropostas.menssagem = userProposta.menssagem

                                            sharedViewModelPerfilPropostas.de = userProposta.de

                                            val userPropostaDe = userProposta.de

                                            if(userPropostaDe != null){
                                                sharedViewModelPerfilPropostasDe.id = userPropostaDe.id
                                                sharedViewModelPerfilPropostasDe.nome_time = userPropostaDe.nome_time
                                                sharedViewModelPerfilPropostasDe.jogo = userPropostaDe.jogo
                                                sharedViewModelPerfilPropostasDe.biografia = userPropostaDe.biografia


                                                sharedViewModelPerfilPropostasDe.jogadores = userPropostaDe.jogadores

                                                if(userPropostaDe.jogadores != null){
                                                    for(userPropostaDeJogadores in userPropostaDe.jogadores){
                                                        sharedViewModelPerfilPropostasDeJogadores.id = userPropostaDeJogadores.id
                                                        sharedViewModelPerfilPropostasDeJogadores.nickname = userPropostaDeJogadores.nickname
                                                        sharedViewModelPerfilPropostasDeJogadores.jogo = userPropostaDeJogadores.jogo
                                                        sharedViewModelPerfilPropostasDeJogadores.funcao = userPropostaDeJogadores.funcao
                                                        sharedViewModelPerfilPropostasDeJogadores.elo = userPropostaDeJogadores.elo
                                                    }
                                                }

                                                sharedViewModelPerfilPropostasDe.propostas = userPropostaDe.propostas

                                                if(userPropostaDe.propostas != null){
                                                    for(userPropostaDePropostas in userPropostaDe.propostas) {
                                                        sharedViewModelPerfilPropostasDePropostas.id = userPropostaDePropostas.id
                                                        sharedViewModelPerfilPropostasDePropostas.menssagem = userPropostaDePropostas.menssagem
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                if(playerProfile != null){
                                    sharedViewModelPlayerProfile.id = playerProfile.id
                                    sharedViewModelPlayerProfile.nickname = playerProfile.nickname
                                    sharedViewModelPlayerProfile.jogo = playerProfile.jogo
                                    sharedViewModelPlayerProfile.funcao = playerProfile.funcao
                                    sharedViewModelPlayerProfile.elo = playerProfile.elo

                                    sharedViewModelPlayerProfile.time_atual = playerProfile.time_atual

                                    val playerProfileTimeAtual = playerProfile.time_atual

                                    if(playerProfileTimeAtual != null){
                                        sharedViewModelPlayerProfileTimeAtual.id = playerProfileTimeAtual.id
                                        sharedViewModelPlayerProfileTimeAtual.nome_time = playerProfileTimeAtual.nome_time
                                        sharedViewModelPlayerProfileTimeAtual.jogo = playerProfileTimeAtual.jogo
                                        sharedViewModelPlayerProfileTimeAtual.biografia = playerProfileTimeAtual.biografia

                                        sharedViewModelPlayerProfileTimeAtual.jogadores = playerProfileTimeAtual.jogadores

                                        sharedViewModelPlayerProfileTimeAtual.propostas = playerProfileTimeAtual.propostas

                                        val playerProfileTimeAtualJogadores = playerProfileTimeAtual.jogadores

                                        if(playerProfileTimeAtualJogadores != null){
                                            for(playerProfileTimeAtualJogadoresList in playerProfileTimeAtualJogadores){
                                                sharedViewModelPlayerProfileTimeAtualJogadores.id = playerProfileTimeAtualJogadoresList.id
                                                sharedViewModelPlayerProfileTimeAtualJogadores.nickname = playerProfileTimeAtualJogadoresList.nickname
                                                sharedViewModelPlayerProfileTimeAtualJogadores.jogo = playerProfileTimeAtualJogadoresList.jogo
                                                sharedViewModelPlayerProfileTimeAtualJogadores.funcao = playerProfileTimeAtualJogadoresList.funcao
                                                sharedViewModelPlayerProfileTimeAtualJogadores.elo = playerProfileTimeAtualJogadoresList.elo
                                            }
                                        }
                                        val playerProfileTimeAtualPropostas = playerProfileTimeAtual.propostas

                                        if(playerProfileTimeAtualPropostas != null){
                                            for(playerProfileTimeAtualPropostasList in playerProfileTimeAtualPropostas){
                                                sharedViewModelPlayerProfileTimeAtualPropostas.id = playerProfileTimeAtualPropostasList.id
                                                sharedViewModelPlayerProfileTimeAtualPropostas.menssagem = playerProfileTimeAtualPropostasList.menssagem
                                            }
                                        }

                                    }

                                }





                                Log.d("INFORMAÇOES DE USUARIO 01", "Token: $token, Id: ${sharedViewModelUser.id}, Nome de usuario: ${sharedViewModelUser.nome_usuario}")
                                Log.d("CarregarPerfilUsuarioScreen", "Resposta corpo bem-sucedida: ${response.code()}")

                                if( sharedViewModelUser.id != 0){

                                    Log.d("INFORMAÇOES DE USUARIO 02", "Token: $token, Id: ${sharedViewModelUser.id}, Nome de usuario: ${sharedViewModelUser.nome_usuario}")

                                    onNavigate("home")
                                }

                            } else {
                                // Trate a resposta não bem-sucedida
                                Log.d("PerfilUsuarioJogadorScreen", "Resposta não bem-sucedida: ${response.code()}")
                                // Log de corpo da resposta (se necessário)
                                Log.d(
                                    "PerfilUsuarioJogadorScreen",
                                    "Corpo da resposta: ${response.errorBody()?.string()}"
                                )
                            }
                        }

                        override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                            // Trate o erro de falha na rede.
                            Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
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