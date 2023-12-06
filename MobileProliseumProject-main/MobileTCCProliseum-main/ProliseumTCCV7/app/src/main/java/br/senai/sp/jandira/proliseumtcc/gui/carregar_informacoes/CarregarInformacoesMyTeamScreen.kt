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
import br.senai.sp.jandira.proliseumtcc.model.getTimeById
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdDoUsuario
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUserHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUserRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeById
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesOutroTimeListaJogadoresScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    sharedGetProfileByIdDoUsuario: SharedGetProfileByIdDoUsuario,
    sharedGetProfileByIdUser: SharedGetProfileByIdUser,
    sharedGetProfileByIdUserRedeSocial: SharedGetProfileByIdUserRedeSocial,
    sharedGetProfileByIdUserHighlights: SharedGetProfileByIdUserHighlights,

    sharedGetProfileByIdPlayerProfile: SharedGetProfileByIdPlayerProfile,
    sharedGetProfileByIdPlayerProfileTimeAtual: SharedGetProfileByIdPlayerProfileTimeAtual,
    sharedGetProfileByIdPlayerProfileTimeAtualJogadores: SharedGetProfileByIdPlayerProfileTimeAtualJogadores,
    sharedGetProfileByIdPlayerProfileTimeAtualPropostas: SharedGetProfileByIdPlayerProfileTimeAtualPropostas,

    sharedGetTimeById: SharedGetTimeById,
    sharedGetTimeByIdTeams: SharedGetTimeByIdTeams,
    sharedGetTimeByIdTeamsDono: SharedGetTimeByIdTeamsDono,
    sharedGetTimeByIdTeamsJogadores: SharedGetTimeByIdTeamsJogadores,
    sharedGetTimeByIdTeamsJogadoresPerfilId: SharedGetTimeByIdTeamsJogadoresPerfilId,
    sharedGetTimeByIdTeamsPropostas: SharedGetTimeByIdTeamsPropostas,



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

                val idOutroJogador = sharedGetProfileByIdPlayerProfileTimeAtual.id

                Log.d(
                    "CarrregarPerfilOutroJogadorListaTimesScreen",
                    "id do jogador escolhido: $idOutroJogador"
                )

                if (token != null && token.isNotEmpty()) {

                    val theGetTimeByIdService = RetrofitFactoryCadastro().theGetTimeByIdService()

                    theGetTimeByIdService.theGetTimeById(idOutroJogador)
                        .enqueue(object : Callback<getTimeById> {
                            override fun onResponse(
                                call: Call<getTimeById>,
                                response: Response<getTimeById>
                            ) {
                                if (response.isSuccessful) {
                                    Log.d(
                                        "CarrregarInformacoesMyTeamScreen",
                                        "CarrregarInformacoesMyTeamScreen, Resposta bem-sucedida: ${response.code()}"
                                    )

                                    val getTimeByIdResponse = response.body()

                                    val teams = getTimeByIdResponse?.teams

                                    sharedGetTimeById.teams = getTimeByIdResponse?.teams



                                    if (teams != null) {
                                        for(teamsList in teams){
                                            val idTeamList = teamsList.id
                                            val nomeTimeTeamList = teamsList.nome_time
                                            val jogoTeamList = teamsList.jogo
                                            val biografiaTeamList = teamsList.biografia

                                            sharedGetTimeByIdTeams.id = teamsList.id
                                            sharedGetTimeByIdTeams.nome_time = teamsList.nome_time
                                            sharedGetTimeByIdTeams.jogo = teamsList.jogo
                                            sharedGetTimeByIdTeams.biografia = teamsList.biografia


                                            val donoIdTeamList = teamsList.dono
                                            val jogadoresTeamList = teamsList.jogadores
                                            val propostasTeamList = teamsList.propostas


                                            sharedGetTimeByIdTeams.dono = teamsList.dono
                                            sharedGetTimeByIdTeams.jogadores = teamsList.jogadores
                                            sharedGetTimeByIdTeams.propostas = teamsList.propostas


                                            if(donoIdTeamList != null){
                                                val idDonoIdTeamList = donoIdTeamList.id
                                                val nomeUsuarioDonoIdTeamList = donoIdTeamList.nome_usuario
                                                val nomeCompletoDonoIdTeamList = donoIdTeamList.nome_completo
                                                val emailDonoIdTeamList = donoIdTeamList.email
                                                val dataNascimentoDonoIdTeamList = donoIdTeamList.data_nascimento
                                                val generoDonoIdTeamList = donoIdTeamList.genero
                                                val nicknameDonoIdTeamList = donoIdTeamList.nickname
                                                val biografiaDonoIdTeamList = donoIdTeamList.biografia

                                                sharedGetTimeByIdTeamsDono.id = donoIdTeamList.id
                                                sharedGetTimeByIdTeamsDono.nome_usuario = donoIdTeamList.nome_usuario
                                                sharedGetTimeByIdTeamsDono.nome_completo = donoIdTeamList.nome_completo
                                                sharedGetTimeByIdTeamsDono.email = donoIdTeamList.email
                                                sharedGetTimeByIdTeamsDono.data_nascimento = donoIdTeamList.data_nascimento
                                                sharedGetTimeByIdTeamsDono.genero = donoIdTeamList.genero
                                                sharedGetTimeByIdTeamsDono.nickname = donoIdTeamList.nickname
                                                sharedGetTimeByIdTeamsDono.biografia = donoIdTeamList.biografia

                                            }

                                            if(jogadoresTeamList != null){
                                                for(jogadoresTeamListJogadores in jogadoresTeamList){
                                                    val idJogadorTeamListJogadores = jogadoresTeamListJogadores.id
                                                    val nicknameJogadorTeamListJogadores = jogadoresTeamListJogadores.nickname
                                                    val jogoJogadorTeamListJogadores = jogadoresTeamListJogadores.jogo
                                                    val funcaoJogadorTeamListJogadores = jogadoresTeamListJogadores.funcao
                                                    val eloJogadorTeamListJogadores = jogadoresTeamListJogadores.elo

                                                    val perfilIdJogadorTeamListJogadores = jogadoresTeamListJogadores.perfil_id

                                                    sharedGetTimeByIdTeamsJogadores.id = jogadoresTeamListJogadores.id
                                                    sharedGetTimeByIdTeamsJogadores.nickname = jogadoresTeamListJogadores.nickname
                                                    sharedGetTimeByIdTeamsJogadores.jogo = jogadoresTeamListJogadores.jogo
                                                    sharedGetTimeByIdTeamsJogadores.funcao = jogadoresTeamListJogadores.funcao
                                                    sharedGetTimeByIdTeamsJogadores.elo = jogadoresTeamListJogadores.elo

                                                    sharedGetTimeByIdTeamsJogadores.perfil_id = jogadoresTeamListJogadores.perfil_id

                                                    if(perfilIdJogadorTeamListJogadores != null){
                                                        val idPerfilIdJogadorTeamListJogadores = perfilIdJogadorTeamListJogadores.id
                                                        val nomeUsuarioPerfilIdJogadorTeamListJogadores = perfilIdJogadorTeamListJogadores.nome_usuario
                                                        val nomeCompletoPerfilIdJogadorTeamListJogadores = perfilIdJogadorTeamListJogadores.nome_completo
                                                        val emailPerfilIdJogadorTeamListJogadores = perfilIdJogadorTeamListJogadores.email
                                                        val dataNascimentoPerfilIdJogadorTeamListJogadores = perfilIdJogadorTeamListJogadores.data_nascimento
                                                        val generoPerfilIdJogadorTeamListJogadores = perfilIdJogadorTeamListJogadores.genero
                                                        val nicknamePerfilIdJogadorTeamListJogadores = perfilIdJogadorTeamListJogadores.nickname
                                                        val biografiaPerfilIdJogadorTeamListJogadores = perfilIdJogadorTeamListJogadores.biografia

                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.id = perfilIdJogadorTeamListJogadores.id
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.nome_usuario = perfilIdJogadorTeamListJogadores.nome_usuario
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.nome_completo = perfilIdJogadorTeamListJogadores.nome_completo
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.email = perfilIdJogadorTeamListJogadores.email
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.data_nascimento = perfilIdJogadorTeamListJogadores.data_nascimento
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.genero = perfilIdJogadorTeamListJogadores.genero
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.nickname = perfilIdJogadorTeamListJogadores.nickname
                                                        sharedGetTimeByIdTeamsJogadoresPerfilId.biografia = perfilIdJogadorTeamListJogadores.biografia
                                                    }

                                                }
                                            }

                                            if(propostasTeamList != null){
                                                for(propostasTeamListPropostas in propostasTeamList){
                                                    val idPropostasTeamListPropostas = propostasTeamListPropostas.id
                                                    val menssagemPropostasTeamListPropostas = propostasTeamListPropostas.menssagem

                                                    sharedGetTimeByIdTeamsPropostas.id = propostasTeamListPropostas.id
                                                    sharedGetTimeByIdTeamsPropostas.menssagem = propostasTeamListPropostas.menssagem

                                                }
                                            }



                                        }
                                    }

                                    onNavigate("perfil_outro_time_lista_jogadores")


                                } else {
                                    // Trate a resposta não bem-sucedida
                                    Log.d(
                                        "CarrregarInformacoesMyTeamScreen",
                                        "CarrregarInformacoesMyTeamScreen, Resposta não bem-sucedida: ${response.code()}"
                                    )
                                    // Log de corpo da resposta (se necessário)
                                    Log.d(
                                        "CarrregarInformacoesMyTeamScreen",
                                        "Corpo da resposta: ${response.errorBody()?.string()}"
                                    )
                                }
                            }

                            override fun onFailure(
                                call: Call<getTimeById>,
                                t: Throwable
                            ) {
                                // Trate o erro de falha na rede.
                                Log.d(
                                    "CarrregarInformacoesMyTeamScreen",
                                    "Erro de rede: ${t.message}"
                                )
                            }

                        })

                } else {
                    Log.e("TOKEN NULO", "o token esta nulo, carregando informaçoes")
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(),
                        color = RedProliseum
                    )
                }
            }


        }
    }
}