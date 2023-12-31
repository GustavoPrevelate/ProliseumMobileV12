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
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesPerfilOutroJogadorScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditarOutro: SharedViewModelPerfilOutro,
    sharedViewModelPerfilJogadorOutro: SharedViewModelPerfilJogadorOutro,
    sharedViewModelPerfilOrganizadorOutro: SharedViewModelPerfilOrganizadorOutro,

    sharedGetProfileByIdDoUsuario: SharedGetProfileByIdDoUsuario,
    sharedGetProfileByIdUser: SharedGetProfileByIdUser,
    sharedGetProfileByIdUserRedeSocial: SharedGetProfileByIdUserRedeSocial,
    sharedGetProfileByIdUserHighlights: SharedGetProfileByIdUserHighlights,

    sharedGetProfileByIdPlayerProfile: SharedGetProfileByIdPlayerProfile,
    sharedGetProfileByIdPlayerProfileTimeAtual: SharedGetProfileByIdPlayerProfileTimeAtual,
    sharedGetProfileByIdPlayerProfileTimeAtualJogadores: SharedGetProfileByIdPlayerProfileTimeAtualJogadores,
    sharedGetProfileByIdPlayerProfileTimeAtualPropostas: SharedGetProfileByIdPlayerProfileTimeAtualPropostas,

    sharedViewModelNomeJogadorListaJogadores: SharedViewModelNomeJogadorListaJogadores,
    sharedViewModelGetListaJogadores: SharedViewModelGetListaJogadores,
    sharedViewModelGetListaJogadoresList: SharedViewModelGetListaJogadoresList,
    sharedViewModelGetListaJogadoresInfoPerfil: SharedViewModelGetListaJogadoresInfoPerfil,
    sharedViewModelGetListaJogadoresTimeAtual: SharedViewModelGetListaJogadoresTimeAtual,
    sharedViewModelGetListaJogadoresDentroDeTime: SharedViewModelGetListaJogadoresDentroDeTime,
    sharedViewModelGetListaJogadoresDentroDeTimeList: SharedViewModelGetListaJogadoresDentroDeTimeList,
    sharedViewModelGetListaJogadoresPropostasList: SharedViewModelGetListaJogadoresPropostasList,
    sharedViewModelGetListaJogadoresPropostasRecebidas: SharedViewModelGetListaJogadoresPropostasRecebidas,
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

                // Se o tempo de espera terminou, continue com a validação do token
                // Restante do código aqui
                val token = sharedViewModelTokenEId.token
                // Restante do seu código de validação do token
                Log.d("CarregarPerfilUsuarioScreen", "Token: $token")

                val idOutroJogador = sharedViewModelGetListaJogadoresInfoPerfil.idInfoPerfilJogador

                if(token != null && token.isNotEmpty()){

                    val profileOutroJogadorService = RetrofitFactoryCadastro().getJogadoresByIdService()

                    profileOutroJogadorService.getProfileById(idOutroJogador).enqueue(object : Callback<GetProfileByIdDoUsuario> {
                        override fun onResponse(call: Call<GetProfileByIdDoUsuario>, response: Response<GetProfileByIdDoUsuario>) {
                            if (response.isSuccessful) {
                                Log.d("carregarInfoOutroJogadorScreen", "carregarInfoOutroJogadorScreen Resposta bem-sucedida: ${response.code()}")
                                val profileResponseData = response.body()

                                val user = profileResponseData?.user
                                val playerProfile = profileResponseData?.playerProfile

                                sharedGetProfileByIdDoUsuario.user = profileResponseData?.user
                                sharedGetProfileByIdDoUsuario.playerProfile = profileResponseData?.playerProfile

                                if(user != null){

                                    val idOutroUser = user.id
                                    val nomeUsuarioOutroUser = user.nome_usuario
                                    val nomeCompletoOutroUser = user.nome_completo
                                    val emailOutroUser = user.email
                                    val dataNascimentoOutroUser = user.data_nascimento
                                    val generoOutroUser = user.genero
                                    val nicknameOutroUser = user.nickname
                                    val biografiaOutroUser = user.biografia

                                    sharedGetProfileByIdUser.id = user.id
                                    sharedGetProfileByIdUser.nome_usuario = user.nome_usuario
                                    sharedGetProfileByIdUser.nome_completo = user.nome_completo
                                    sharedGetProfileByIdUser.email = user.email
                                    sharedGetProfileByIdUser.data_nascimento = user.data_nascimento
                                    sharedGetProfileByIdUser.genero = user.genero
                                    sharedGetProfileByIdUser.nickname = user.nickname
                                    sharedGetProfileByIdUser.biografia = user.biografia

                                    val redeSocialOutroUser = user.redeSocial
                                    val highlightsOutroUser = user.highlights

                                    sharedGetProfileByIdUser.redeSocial = user.redeSocial
                                    sharedGetProfileByIdUser.highlights = user.highlights

                                    if(redeSocialOutroUser != null){
                                        for(redeSocialOutroUserPerfil in redeSocialOutroUser){

                                            val idRedeSocial = redeSocialOutroUserPerfil.id
                                            val linkRedeSocial = redeSocialOutroUserPerfil.link
                                            val tipoRedeSocial = redeSocialOutroUserPerfil.tipo

                                            sharedGetProfileByIdUserRedeSocial.id = redeSocialOutroUserPerfil.id
                                            sharedGetProfileByIdUserRedeSocial.link = redeSocialOutroUserPerfil.link
                                            sharedGetProfileByIdUserRedeSocial.tipo = redeSocialOutroUserPerfil.tipo


                                        }
                                    }

                                    if(highlightsOutroUser != null){
                                        for(highLightsOutroUserProfile in highlightsOutroUser){
                                            val idHighlightsOutroUserProfile = highLightsOutroUserProfile.id
                                            val tituloHighlightsOutroUserProfile = highLightsOutroUserProfile.titulo

                                            sharedGetProfileByIdUserHighlights.id = highLightsOutroUserProfile.id
                                            sharedGetProfileByIdUserHighlights.titulo = highLightsOutroUserProfile.titulo
                                        }
                                    }
                                }


                                if (playerProfile != null) {
                                    val idPlayerProfileOutroUser = playerProfile.id
                                    val nicknamePlayerProfileOutroUser = playerProfile.nickname
                                    val jogoPlayerProfileOutroUser = playerProfile.jogo
                                    val funcaoPlayerProfileOutroUser = playerProfile.funcao
                                    val eloPlayerProfileOutroUser = playerProfile.elo

                                    sharedGetProfileByIdPlayerProfile.id = playerProfile.id
                                    sharedGetProfileByIdPlayerProfile.nickname = playerProfile.nickname
                                    sharedGetProfileByIdPlayerProfile.jogo = playerProfile.jogo
                                    sharedGetProfileByIdPlayerProfile.funcao = playerProfile.funcao
                                    sharedGetProfileByIdPlayerProfile.elo = playerProfile.elo


                                    sharedGetProfileByIdPlayerProfile.time_atual = playerProfile.time_atual

                                    val timeAtualPlayerProfileOutroUser = playerProfile.time_atual

                                    if(timeAtualPlayerProfileOutroUser != null){
                                        val idTimeAtualPlayerProfileOutroUser = timeAtualPlayerProfileOutroUser.id
                                        val nomeTimeTimeAtualPlayerProfileOutroUser = timeAtualPlayerProfileOutroUser.nome_time
                                        val jogoTimeAtualPlayerProfileOutroUser = timeAtualPlayerProfileOutroUser.jogo
                                        val biografiaTimeAtualPlayerProfileOutroUser = timeAtualPlayerProfileOutroUser.biografia


                                        sharedGetProfileByIdPlayerProfileTimeAtual.id = timeAtualPlayerProfileOutroUser.id
                                        sharedGetProfileByIdPlayerProfileTimeAtual.nome_time = timeAtualPlayerProfileOutroUser.nome_time
                                        sharedGetProfileByIdPlayerProfileTimeAtual.jogo = timeAtualPlayerProfileOutroUser.jogo
                                        sharedGetProfileByIdPlayerProfileTimeAtual.biografia = timeAtualPlayerProfileOutroUser.biografia

                                        val jogadoresTimeAtualPlayerProfileOutroUser = timeAtualPlayerProfileOutroUser.jogadores
                                        val propostasTimeAtualPlayerProfileOutroUser = timeAtualPlayerProfileOutroUser.propostas

                                        sharedGetProfileByIdPlayerProfileTimeAtual.jogadores = timeAtualPlayerProfileOutroUser.jogadores
                                        sharedGetProfileByIdPlayerProfileTimeAtual.propostas = timeAtualPlayerProfileOutroUser.propostas

                                        if(jogadoresTimeAtualPlayerProfileOutroUser != null){
                                            for(jogadoresTimeAtualPlayerProfileOutroUserList in jogadoresTimeAtualPlayerProfileOutroUser){
                                                val idJogadorTimeAtualplayerProfileOutroUser = jogadoresTimeAtualPlayerProfileOutroUserList.id
                                                val nicknameJogadorTimeAtualplayerProfileOutroUser  = jogadoresTimeAtualPlayerProfileOutroUserList.nickname
                                                val jogoJogadorTimeAtualplayerProfileOutroUser  = jogadoresTimeAtualPlayerProfileOutroUserList.jogo
                                                val funcaoJogadorTimeAtualplayerProfileOutroUser  = jogadoresTimeAtualPlayerProfileOutroUserList.funcao
                                                val eloJogadorTimeAtualplayerProfileOutroUser  = jogadoresTimeAtualPlayerProfileOutroUserList.elo

                                                sharedGetProfileByIdPlayerProfileTimeAtualJogadores.id = jogadoresTimeAtualPlayerProfileOutroUserList.id
                                                sharedGetProfileByIdPlayerProfileTimeAtualJogadores.nickname = jogadoresTimeAtualPlayerProfileOutroUserList.nickname
                                                sharedGetProfileByIdPlayerProfileTimeAtualJogadores.jogo = jogadoresTimeAtualPlayerProfileOutroUserList.jogo
                                                sharedGetProfileByIdPlayerProfileTimeAtualJogadores.funcao = jogadoresTimeAtualPlayerProfileOutroUserList.funcao
                                                sharedGetProfileByIdPlayerProfileTimeAtualJogadores.elo = jogadoresTimeAtualPlayerProfileOutroUserList.elo
                                            }
                                        }

                                        if(propostasTimeAtualPlayerProfileOutroUser != null){
                                            for(propostasTimeAtualPlayerProfileOutroUserlist in propostasTimeAtualPlayerProfileOutroUser){
                                                val idPropostasTimeAtualPlayerProfileOutroUserlist = propostasTimeAtualPlayerProfileOutroUserlist.id
                                                val menssagemPropostasTimeAtualPlayerProfileOutroUserlist = propostasTimeAtualPlayerProfileOutroUserlist.menssagem

                                                sharedGetProfileByIdPlayerProfileTimeAtualPropostas.id = propostasTimeAtualPlayerProfileOutroUserlist.id
                                                sharedGetProfileByIdPlayerProfileTimeAtualPropostas.menssagem = propostasTimeAtualPlayerProfileOutroUserlist.menssagem

                                            }
                                        }
                                    }





                                }




                                Log.d("INFORMAÇOES DE OUTRO USUARIO", "carregarInfoOutroJogadorScreen Token: $token, Id: ${sharedGetProfileByIdUser.id }, Nome de usuario: ${sharedGetProfileByIdUser.nome_usuario }")
                                Log.d("carregarInfoOutroJogadorScreen", "carregarInfoOutroJogadorScreen Resposta corpo bem-sucedida: ${response.code()}")

                                if( sharedGetProfileByIdUser.id != 0){

                                    Log.d("INFORMAÇOES DE OUTRO USUARIO", "Token: $token, Id: ${sharedGetProfileByIdUser.id}, Nome de usuario: ${sharedGetProfileByIdUser.nome_usuario}, id do time ${sharedGetProfileByIdPlayerProfileTimeAtual.id} e nome do time ${sharedGetProfileByIdPlayerProfileTimeAtual.nome_time}")

                                    onNavigate("perfil_de_outro_jogador_lista_jogadores")
                                }

//                            onNavigate("home")
                            } else {
                                // Trate a resposta não bem-sucedida
                                Log.d("carregarInfoOutroJogadorScreen",
                                    "carregarInfoOutroJogadorScreen Resposta não bem-sucedida: ${response.code()}")
                                // Log de corpo da resposta (se necessário)
                                Log.d(
                                    "carregarInfoOutroJogadorScreen",
                                    "carregarInfoOutroJogadorScreen Corpo da resposta: ${response.errorBody()?.string()}"
                                )
                            }
                        }

                        override fun onFailure(call: Call<GetProfileByIdDoUsuario>, t: Throwable) {
                            // Trate o erro de falha na rede.
                            Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
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