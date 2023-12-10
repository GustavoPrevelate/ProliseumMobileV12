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
import br.senai.sp.jandira.proliseumtcc.components.Genero
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagens
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsPropostas
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
fun CarregarTelaInscritosPostagemTimeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    sharedGetTimeListaPostagens: SharedGetTimeListaPostagens,
    sharedGetTimeListaPostagensPublicacao: SharedGetTimeListaPostagensPublicacao,
    sharedGetTimeListaPostagensPublicacaoDonoId: SharedGetTimeListaPostagensPublicacaoDonoId,
    sharedGetTimeListaPostagensPublicacaoTime: SharedGetTimeListaPostagensPublicacaoTime,
    sharedGetTimeListaPostagensPublicacaoTimeJogadores: SharedGetTimeListaPostagensPublicacaoTimeJogadores,
    sharedGetTimeListaPostagensPublicacaoTimePropostas: SharedGetTimeListaPostagensPublicacaoTimePropostas,
    onNavigate: (String) -> Unit
) {

    //CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 0.5 segundos antes de continuar
        delay(1000)
        loading = false
    }

    var idTimeEscolhido by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacaoTime.id) }


    var selectedGeneroUser by remember { mutableStateOf<Genero?>(null) }


    LaunchedEffect(sharedGetTimeListaPostagensPublicacaoTime) {
        idTimeEscolhido = sharedGetTimeListaPostagensPublicacaoTime.id

    }

    Log.e("id do time", "ID DO TIME ESCOLHIDO ANTES DAS INSCRICOES ${idTimeEscolhido}")

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

                    sharedGetTimeListaPostagensPublicacaoTime.id = idTimeEscolhido


                    onNavigate("lista_inscricoes_para_times")

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