package br.senai.sp.jandira.proliseumtcc.gui.gerenciar_time

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
import br.senai.sp.jandira.proliseumtcc.model.AdicionarJogadorAoTime
import br.senai.sp.jandira.proliseumtcc.model.PutAtualizarMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.model.ResponsePutAtualizarMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeAdded
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadoresPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun EntrarNoTimeScreen(
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

    // SharedViewModel GET TIME FILTER
    sharedGetTime: SharedGetTime,
    sharedGetTimeTeams: SharedGetTimeTeams,
    sharedGetTimeTeamsJogadores: SharedGetTimeTeamsJogadores,
    sharedGetTimeTeamsJogadoresPerfilId: SharedGetTimeTeamsJogadoresPerfilId,
    sharedGetTimeDono: SharedGetTimeDono,
    sharedGetTimeTeamsPropostas: SharedGetTimeTeamsPropostas,
    sharedViewModelImageUri: SharedViewModelImageUri,

    // SharedViewModel PUT ADICIONAR JOGADOR AO TIME
    sharedAdicionarJogadorAoTimeAdded: SharedAdicionarJogadorAoTimeAdded,
    sharedAdicionarJogadorAoTime: SharedAdicionarJogadorAoTime,
    sharedAdicionarJogadorAoTimeDono: SharedAdicionarJogadorAoTimeDono,
    sharedAdicionarJogadorAoTimeJogadores: SharedAdicionarJogadorAoTimeJogadores,
    sharedAdicionarJogadorAoTimeJogadoresPerfilId: SharedAdicionarJogadorAoTimeJogadoresPerfilId,
    sharedAdicionarJogadorAoTimeJogadoresTimeAtual: SharedAdicionarJogadorAoTimeJogadoresTimeAtual,
    saredAdicionarJogadorAoTimeJogadoresTimeAtualJogadores: SharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadores,
    sharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadoresPropostas: SharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadoresPropostas,
    sharedAdicionarJogadorAoTimePropostas: SharedAdicionarJogadorAoTimePropostas,
    onNavigate: (String) -> Unit
) {


    //CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 0.5 segundos antes de continuar
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

            } else {

                //TOKEN
                val token = sharedViewModelTokenEId.token

                if(token != null && token.isNotEmpty()){

                    val idUserVaiEntrarNoTime = sharedViewModelUser.id

                    val idDoTime = sharedGetTimeTeams.id

                    val token = sharedViewModelTokenEId.token

                    val entrarNoTimeService = RetrofitFactoryCadastro().EntrarNoMeuTimeService()

                    entrarNoTimeService.putTeamTimeJogador("Bearer " + token, idDoTime, idUserVaiEntrarNoTime)
                        .enqueue(object : Callback<AdicionarJogadorAoTime> {
                            override fun onResponse(call: Call<AdicionarJogadorAoTime>, response: Response<AdicionarJogadorAoTime>) {
                                if (response.isSuccessful) {

                                    Log.d(
                                        "ENTROU!",
                                        "ENTROU NO TIME COM SUCESSO: ${response.code()}"
                                    )

                                    onNavigate("carregar_informacoes_lista_times")

                                } else {

                                    Log.d(
                                        "NÃO ENTROU",
                                        "Falha ao entrar no time: ${response.code()}"
                                    )
                                    // Log do corpo da resposta (se necessário)
                                    Log.d(
                                        "BODY NÃO ENTROU",
                                        "Corpo da resposta: ${response.errorBody()?.string()}"
                                    )
                                }
                            }

                            override fun onFailure(call: Call<AdicionarJogadorAoTime>, t: Throwable) {

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