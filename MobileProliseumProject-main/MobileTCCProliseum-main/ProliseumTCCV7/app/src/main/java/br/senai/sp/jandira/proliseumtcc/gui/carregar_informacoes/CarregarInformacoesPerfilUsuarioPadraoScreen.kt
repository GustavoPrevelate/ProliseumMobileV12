package br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTime
import br.senai.sp.jandira.proliseumtcc.model.MeusTimes
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUserHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUserRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUserTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUserTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUserTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeRedeSocial
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
    sharedViewModelPerfilPropostasDeRedeSocial: SharedViewModelPerfilPropostasDeRedeSocial,
    sharedViewModelPerfilPropostasDeHighlights: SharedViewModelPerfilPropostasDeHighlights,

    sharedViewModelPlayerProfile: SharedViewModelPlayerProfile,
    sharedViewModelPlayerProfileTimeAtual: SharedViewModelPlayerProfileTimeAtual,
    sharedViewModelPlayerProfileTimeAtualJogadores: SharedViewModelPlayerProfileTimeAtualJogadores,
    sharedViewModelPlayerProfileTimeAtualPropostas: SharedViewModelPlayerProfileTimeAtualPropostas,

    //SharedViewModel GET MY TIME ATUALIZADO
    sharedAGetMyTime: SharedAGetMyTime,
    sharedAGetMyTimeTime: SharedAGetMyTimeTime,
    sharedAGetMyTimeTimeJogadores: SharedAGetMyTimeTimeJogadores,
    sharedAGetMyTimeTimePropostas: SharedAGetMyTimeTimePropostas,

    sharedAGetMyTimeUser: SharedAGetMyTimeUser,
    sharedAGetMyTimeUserHighlights: SharedAGetMyTimeUserHighlights,
    sharedAGetMyTimeUserPropostas: SharedAGetMyTimeUserPropostas,
    sharedAGetMyTimeUserRedeSocial: SharedAGetMyTimeUserRedeSocial,

    onNavigate: (String) -> Unit
) {

    //CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 3 segundos antes de continuar
        delay(3000)
        loading = false
    }

    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.logo_proliseum),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(
                        text = "CARREGANDO",
                        fontFamily = customFontFamilyText,
                        fontSize = 32.sp,
                        fontWeight = FontWeight(900),
                        color = Color.White
                    )
                }

                CircularProgressIndicator(
                    modifier = Modifier
                        .height(300.dp)
                        .width(300.dp)
                    ,
                    color = RedProliseum
                )
            }

            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_proliseum),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(
                            text = "CARREGANDO",
                            fontFamily = customFontFamilyText,
                            fontSize = 32.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(300.dp)
                            .width(300.dp)
                        ,
                        color = RedProliseum
                    )
                }

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

                                                sharedViewModelPerfilPropostasDe.redeSocial = userPropostaDe.redeSocial

                                                if(userPropostaDe.redeSocial != null){
                                                    for(userPropostaDeRedeSocial in userPropostaDe.redeSocial){
                                                        sharedViewModelPerfilPropostasDeRedeSocial.id = userPropostaDeRedeSocial.id
                                                        sharedViewModelPerfilPropostasDeRedeSocial.tipo = userPropostaDeRedeSocial.tipo
                                                        sharedViewModelPerfilPropostasDeRedeSocial.link = userPropostaDeRedeSocial.link


                                                    }
                                                }

                                                sharedViewModelPerfilPropostasDe.highlights = userPropostaDe.highlights

                                                if(userPropostaDe.highlights != null){
                                                    for(userPropostaDeHighLights in userPropostaDe.highlights){

                                                        sharedViewModelPerfilPropostasDeHighlights.id = userPropostaDeHighLights.id
                                                        sharedViewModelPerfilPropostasDeHighlights.titulo = userPropostaDeHighLights.titulo

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
                            Log.d("CAR INFO PERFIL PADRAO", "Erro de rede da tela de carregar informacoes perfil usuario padrao: ${t.message}")
                        }

                    })





                } else{
                    Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.logo_proliseum),
                                contentDescription = "",
                                modifier = Modifier.size(80.dp)
                            )

                            Spacer(modifier = Modifier.width(20.dp))

                            Text(
                                text = "CARREGANDO",
                                fontFamily = customFontFamilyText,
                                fontSize = 32.sp,
                                fontWeight = FontWeight(900),
                                color = Color.White
                            )
                        }

                        CircularProgressIndicator(
                            modifier = Modifier
                                .height(300.dp)
                                .width(300.dp)
                            ,
                            color = RedProliseum
                        )
                    }
                }
            }
        }
    }
}